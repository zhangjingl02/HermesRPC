package config;

import com.ecwid.consul.v1.ConsulClient;
import discovery.DiscoveryClient;
import discovery.consul.ConsulDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import serviceregistry.ServiceRegistry;
import serviceregistry.consul.ConsulServiceRegistry;

@Configuration
@ConditionalOnConsulEnabled
public class RpcConsulAutoConfiguration {

    @Bean
    public DiscoveryClient discoveryClient(RpcProperties properties){
        DiscoveryClient discoveryClient= new ConsulDiscoveryClient(consulClient(properties),properties);
        return discoveryClient;
    }

    @Bean
    public ConsulClient consulClient(RpcProperties properties){
        return new ConsulClient(properties.getConsul().getHost(),properties.getConsul().getPort());
    }

    @Bean
    public ServiceRegistry serviceRegistry(RpcProperties properties){
        ServiceRegistry registry=new ConsulServiceRegistry(consulClient(properties),properties);
        return registry;
    }
}
