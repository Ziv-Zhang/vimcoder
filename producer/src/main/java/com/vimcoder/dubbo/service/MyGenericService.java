package com.vimcoder.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;

@Service
public class MyGenericService implements GenericService {

	@Override
	public Object $invoke(String method, String[] parameterTypes, Object[] args)
		throws GenericException {
		if("hello".equals(method)){
			return "Welcome " + args[0];
		}
		return "error";
	}

}
