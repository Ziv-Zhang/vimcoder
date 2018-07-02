package com.vimcoder;

import org.springframework.context.annotation.Bean;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;

//@Configuration
public class DubboConfiguration {

	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig application = new ApplicationConfig();
		application.setName("dubbo-customer1");
		return application;
	}

	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://localhost:2181");
		// registry.setCheck(false); //启动服务检查
		return registry;
	}

	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		protocol.setPort(20880);
		return protocol;
	}

	@Bean
	public ConsumerConfig consumerConfig() {
		ConsumerConfig consumer = new ConsumerConfig();
		consumer.setTimeout(5000);
		return consumer;
	}

}
