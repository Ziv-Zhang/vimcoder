package com.vimcoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * 结合apring boot，使用API和注解的方式来配置Dubbo
 * 
 * @author apple
 */
@Configuration
public class DubboConfiguration {

	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig cfg = new ApplicationConfig();
		cfg.setName("dubbo-provider");
		return cfg;
	}

	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://localhost:2181");
		return registry;
	}

	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		protocol.setPort(20880);
		return protocol;
	}

}
