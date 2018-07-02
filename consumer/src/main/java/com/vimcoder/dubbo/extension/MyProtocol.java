package com.vimcoder.dubbo.extension;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.RpcException;

public class MyProtocol implements Protocol {

	@Override
	public int getDefaultPort() {
		return 90088;
	}

	@Override
	public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
		return null;
	}

	@Override
	public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
		return null;
	}

	@Override
	public void destroy() {

	}

}
