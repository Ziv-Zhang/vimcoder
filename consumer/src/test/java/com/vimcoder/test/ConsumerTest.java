package com.vimcoder.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.vimcoder.ConsumerApplication;
import com.vimcoder.dubbo.bean.User;
import com.vimcoder.dubbo.service.ICallbackListener;
import com.vimcoder.dubbo.service.ICallbackService;
import com.vimcoder.dubbo.service.IDemoService;
import com.vimcoder.dubbo.service.IMergeGroup;
import com.vimcoder.dubbo.service.IMockService;
import com.vimcoder.dubbo.service.IStubService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)
public class ConsumerTest {

	@Reference
	private IDemoService demoService;

	@Autowired
	private IMergeGroup mergeGroup;

	@Reference
	private GenericService genericService;
	@Reference
	private ICallbackService callbackService;
	@Reference(stub = "com.vimcoder.dubbo.service.StubServiceProxy")
	private IStubService stubService;
	@Reference(mock = "com.vimcoder.dubbo.service.MockServiceMock", check = false)
	private IMockService mockService;

	@Test
	public void demoService() {
		System.out.println(demoService.getName());
	}

	/**
	 * 分组聚合
	 */
	@Test
	public void mergeService() {
		System.out.println("从服务端获取信息：" + mergeGroup.list());
	}

	/**
	 * 泛化实现
	 */
	@Test
	public void genericService() {
		System.out.println("genericService结果:" + genericService.$invoke("hello",
			new String[]{"java.long.String"}, new Object[]{"world", "ziv"}));
	}
	
	/**
	 * 回声测试
	 * 
	 * 用于检测服务是否可用，回声测试按照正常请求流程执行，能够测试整个调用是否通畅，可用于监控。
	 * 所有服务自动实现 EchoService 接口，只需将任意服务引用强制转型为 EchoService，即可使用。
	 */
	@Test
	public void echoService() {
		EchoService echoService = (EchoService)demoService;
		Object $echo = echoService.$echo("hello");
		System.out.println("echoService invoke");
		assertEquals($echo, "hello");
	}

	/**
	 * 使用callbacklistener进行回调
	 */
	@Test
	public void callbackService() {
		callbackService.addListener("key1", new ICallbackListener() {
			@Override
			public void changed(String msg) {
				System.out.println(msg);
			}
		});
	}

	/**
	 * 本地存根，暂时无效
	 */
	@Test
	public void stubService() {
		User user = stubService.user(1);
		System.out.println(user);
	}
	
	/**
	 * 本地伪装(无效)
	 */
	@Test
	public void mockService() {
		mockService.hello();
	}
}
