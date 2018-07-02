package com.vimcoder.dubbo.service;

import com.vimcoder.dubbo.bean.User;

public class StubServiceProxy implements IStubService {

	private IStubService service;

	// 构造方法需要传入代理对象
	public StubServiceProxy(IStubService service) {
		System.out.println("autowired for sonsumer");
		this.service = service;
	}

	@Override
	public User user(int id) {
		System.out.println("stub service invoked for consumer");
		return service.user(id);
	}

}
