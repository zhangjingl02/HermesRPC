package serviceregistry;

/**
 * Created by esvwwxn on 8/21/2017.
 */
public interface ServiceRegistry {

	/**
	 * Register the registration. Registrations typically have information about
	 * instances such as: hostname and port.
	 * @param instance the instance
	 */
	void register(ServiceInstance instance);

	/**
	 * Deregister the registration.
	 * @param instance
	 */
	void deregister(ServiceInstance instance);

	/**
	 * Close the ServiceRegistry. This a lifecycle method.
	 */
	void close();

//	/**
//	 * Sets the status of the registration. The status values are determined
//	 * by the individual implementations.
//	 *
//	 * @param instance the instance to update
//	 * @param status the status to set
//	 */
//	void setStatus(ServiceInstance instance, String status);
//
//	/**
//	 * Gets the status of a particular registration.
//	 *
//	 * @param serviceId the serviceId to query
//	 * @param <T> the type of the status
//	 * @return the status of the registration
//	 */
//	<T> T getStatus(String serviceId);
}
