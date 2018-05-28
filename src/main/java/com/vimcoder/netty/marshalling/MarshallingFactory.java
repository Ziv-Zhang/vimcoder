package com.vimcoder.netty.marshalling;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

/**
 * 创建 Marshalling 编解码器的工厂类
 * 
 * @author apple
 *
 */
public class MarshallingFactory {

	/**
	 * 创建Marshalling的解码器
	 * 
	 * @return
	 */
	public static MarshallingDecoder buildMarshallingDecoder() {
		// 首先通过Marshalling工具类的getProvidedMarshallerFactory获取MarshallerFactory实例
		// 参数"serial"获取的是jboss-marshalling-serial包中的实现类
		final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
		// MarshallingDecoder在生成之前做一些配置
		final MarshallingConfiguration config = new MarshallingConfiguration();
		// version默认是5
		config.setVersion(5);
		// 使用DefaultUnmarshallerProvider在后面生成MarshallingDecoder。
		UnmarshallerProvider provider = new DefaultUnmarshallerProvider(factory, config);
		return new MarshallingDecoder(provider);
	}

	/**
	 * 创建Marshalling编码器
	 * 
	 * @return
	 */
	public static MarshallingEncoder buildMarshallingEncoder() {
		final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration config = new MarshallingConfiguration();
		config.setVersion(5);

		MarshallerProvider provider = new DefaultMarshallerProvider(factory, config);
		return new MarshallingEncoder(provider);
	}

}
