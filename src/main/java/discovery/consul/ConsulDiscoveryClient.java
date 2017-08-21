package discovery.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.catalog.model.CatalogService;
import com.ecwid.consul.v1.health.model.HealthService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import config.RpcProperties;
import discovery.DiscoveryClient;
import serviceregistry.ServiceInstance;

import java.util.List;

/**
 * Created by esvwwxn on 8/21/2017.
 */
public class ConsulDiscoveryClient implements DiscoveryClient {

	private ConsulClient consulClient;
	private RpcProperties properties;

	public ConsulDiscoveryClient(ConsulClient consulClient, RpcProperties properties) {
		this.consulClient = consulClient;
		this.properties = properties;
	}

	@Override
	public List<ServiceInstance> getInstances(String serviceName) {
		Response<List<HealthService>> services;
		if (Strings.isNullOrEmpty(properties.getConsul().getAclToken())) {
			services = consulClient.getHealthServices(serviceName, true, QueryParams
					.DEFAULT);
		}else {
			services = consulClient.getHealthServices(serviceName, true, QueryParams
					.DEFAULT,properties.getConsul().getAclToken());
		}

		List<ServiceInstance> serviceInstances= Lists.newArrayList();
		for (HealthService service : services.getValue()){
			ServiceInstance instance = convertService(service);
			serviceInstances.add(instance);
		}

		return serviceInstances;
	}

	private ServiceInstance convertService(HealthService service) {
		ServiceInstance instance=new ServiceInstance();
		instance.setAddress(service.getService().getAddress());
		instance.setPort(service.getService().getPort());
		instance.setServiceId(service.getService().getId());
		instance.setServiceName(service.getService().getService());
		instance.setTags(service.getService().getTags());
		return instance;
	}

	@Override
	public List<String> getServices() {
		return null;
	}
}
