package com.vimcoder.dubbo.service;

public class MockServiceMock implements IMockService {

	@Override
	public String hello() {
		// 容错数据，只在RpcException抛出的时候调用
		return "容错数据";
	}

}
