package com.vimcoder.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcException;

@Service
public class MockService implements IMockService {

	@Override
	public String hello() {
		boolean r = true;
		if(r){
			throw new RpcException();
		}
		return "success";
	}

}
