package com.vimcoder.dubbo.extension;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.RpcException;

public class MyProtocolWrapper implements Protocol {

	private Protocol protocol;

	public MyProtocolWrapper(Protocol protocol) {
		super();
		this.protocol = protocol;
	}

	@Override
	public int getDefaultPort() {
		return protocol.getDefaultPort();
	}

	@Override
	public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
		return protocol.export(invoker);
	}

	@Override
	public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
		return protocol.refer(type, url);
	}

	@Override
	public void destroy() {
		protocol.destroy();
	}

}
