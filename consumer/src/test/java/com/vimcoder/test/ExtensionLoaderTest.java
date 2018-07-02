package com.vimcoder.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;
import com.vimcoder.ConsumerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)
public class ExtensionLoaderTest {

	@Test
	public void myProtocol() {
		Protocol extension = ExtensionLoader.getExtensionLoader(Protocol.class)
			.getExtension("myProtocol");
		System.out.println(extension.getClass().getSimpleName() + ":" + extension.getDefaultPort());
	}
}
