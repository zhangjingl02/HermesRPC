package serviceregistry.consul;

import com.ecwid.consul.ConsulException;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import config.RpcProperties;
import lombok.extern.apachecommons.CommonsLog;
import serviceregistry.ServiceInstance;
import serviceregistry.ServiceRegistry;
import util.IDUtils;

/**
 * Created by esvwwxn on 8/21/2017.
 */
@CommonsLog
public class ConsulServiceRegistry implements ServiceRegistry{

	private ConsulClient consulClient;
	private RpcProperties properties;

	public ConsulServiceRegistry(ConsulClient consulClient, RpcProperties properties) {
		this.consulClient = consulClient;
		this.properties = properties;
	}

	@Override
	public void register(ServiceInstance instance) {

		NewService service=new NewService();
		service.setAddress(instance.getAddress());
		service.setId(IDUtils.uuid());
		service.setName(instance.getServiceName());
		service.setPort(instance.getPort());
		service.setTags(instance.getTags());
		log.info("Registering service with consul:"+service.getName());
		try{
			consulClient.agentServiceRegister(service);
		}catch (ConsulException e){
			log.warn("Error registering service with consul: " + instance.getServiceName(), e);
		}

	}

	@Override
	public void deregister(ServiceInstance instance) {
		log.info("Deregistering service with consul: " + instance.getServiceId());
		consulClient.agentServiceDeregister(instance.getServiceId());
	}

	@Override
	public void close() {

	}

//	@Override
//	public void setStatus(ServiceInstance instance, String status) {
//
//	}

//	@Override
//	public <T> T getStatus(String serviceId) {
//		return null;
//	}
}
