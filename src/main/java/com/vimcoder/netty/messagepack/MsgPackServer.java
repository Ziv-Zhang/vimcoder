package com.vimcoder.netty.messagepack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class MsgPackServer {
	public static void main(String[] args) {
		NioEventLoopGroup boss = new NioEventLoopGroup();
		NioEventLoopGroup work = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(boss, work).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline()
									.addLast(new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2))
									.addLast("msgpack decoder", new MessagePackDecoder())
									.addLast(new LengthFieldPrepender(2))
									.addLast("msgpack encoder", new MessagePackEncoder())
									.addLast(new ChannelHandlerAdapter() {
										int count = 0;

										@Override
										public void channelRead(ChannelHandlerContext ctx,
												Object msg) throws Exception {
											System.out.println(
													"server 收到第 " + (++count) + " 次消息：" + msg);
											ctx.writeAndFlush(
													"server response" + System.lineSeparator());
										}

										@Override
										public void channelReadComplete(ChannelHandlerContext ctx)
												throws Exception {
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
