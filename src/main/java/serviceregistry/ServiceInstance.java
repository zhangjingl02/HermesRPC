package serviceregistry;

import lombok.Data;

import java.util.List;

/**
 * Created by esvwwxn on 8/21/2017.
 */
@Data
public class ServiceInstance {

	private String serviceId;
	private String serviceName;
	private String address;
	private int port;
	private List<String> tags;

}
