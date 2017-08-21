package discovery;

import serviceregistry.ServiceInstance;

import java.util.List;

/**
 * Created by esvwwxn on 8/21/2017.
 */
public interface DiscoveryClient {

	List<ServiceInstance> getInstances(String serviceName);

	List<String> getServices();
}
