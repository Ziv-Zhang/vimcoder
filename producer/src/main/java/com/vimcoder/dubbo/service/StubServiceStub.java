package com.vimcoder.dubbo.service;

import com.vimcoder.dubbo.bean.User;

public class StubServiceStub implements IStubService {

	private IStubService service;

	// 构造方法需要传入代理对象
	public StubServiceStub(IStubService service) {
		super();
		System.out.println("autowired provider");
		this.service = service;
	}

	@Override
	public User user(int id) {
		// 此代码在客户端执行
		try{
			User user = service.user(id);
			System.out.println("客户端执行：" + user);
			return user;
		}catch(Exception e){
			e.printStackTrace();
		}
		// 容错数据
		return new User();
	}

}
