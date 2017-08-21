package config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * Created by esvwwxn on 8/21/2017.
 */
@Data
@Configuration("rpc")
public class RpcProperties {


	private final Consul consul=new Consul();

	@Data
	public static class Consul{
		private String host="127.0.0.1";
		private int port=8500;
		private String aclToken="";
	}

}
