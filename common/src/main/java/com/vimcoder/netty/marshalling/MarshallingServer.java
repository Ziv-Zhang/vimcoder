package com.vimcoder.netty.marshalling;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class MarshallingServer {
	public static void main(String[] args) {
		NioEventLoopGroup boss = new NioEventLoopGroup();
		NioEventLoopGroup work = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(boss, work).channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 1024).handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						System.out.println("init");
						ch.pipeline().addLast(MarshallingFactory.buildMarshallingDecoder())
							.addLast(MarshallingFactory.buildMarshallingEncoder())
							.addLast(new ChannelHandlerAdapter() {
								int count = 0;

								@Override
								public void channelRead(ChannelHandlerContext ctx, Object msg)
									throws Exception {
									System.out.println("server 收到第 " + (++count) + " 次消息：" + msg);

									MarshallingRequest req = new MarshallingRequest();
									req.setMsg("server response");
									ctx.writeAndFlush(req);
								}

								@Override
								public void channelReadComplete(ChannelHandlerContext ctx)
									throws Exception {

								}

								@Override
								public void exceptionCaught(ChannelHandlerContext ctx,
									Throwable cause) throws Exception {
									cause.printStackTrace();
								}
							});
					}
				});

			ChannelFuture sync = b.bind(8080).sync();
			sync.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			work.shutdownGracefully();
		}
	}
}
