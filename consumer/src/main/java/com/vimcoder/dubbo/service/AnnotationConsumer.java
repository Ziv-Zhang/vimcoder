package com.vimcoder.dubbo.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vimcoder.dubbo.bean.User;

@RestController
@RequestMapping("/dubbo")
public class AnnotationConsumer {

	@Reference
	private IDemoService demoService;
	@Reference
	private ICallbackService callback;
	@Reference(stub = "com.vimcoder.dubbo.service.StubServiceProxy")
	private IStubService stubService;
	@Reference(mock = "com.vimcoder.dubbo.service.MockServiceMock", check = false)
	private IMockService mockService;

	@RequestMapping("/callback")
	public String callback() {
		callback.addListener("key1", new ICallbackListener() {

			@Override
			public void changed(String msg) {
				System.out.println(msg);
			}
		});
		return "success";
	}

	@RequestMapping("/stubService")
	public User stubService() {
		return stubService.user(1);
	}

	@RequestMapping("/mockService")
	public String mockService() {
		return mockService.hello();
	}
}
