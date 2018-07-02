package com.vimcoder.dubbo.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.vimcoder.dubbo.bean.User;

@Service
public class StubServiceImpl implements IStubService {

	@Override
	public User user(int id) {
		return new User(id, "zhang", "123456");
	}

}
