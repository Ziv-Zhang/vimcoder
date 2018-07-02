package com.vimcoder.netty.messagepack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class MsgPackClient {
	public static void main(String[] args) {
		NioEventLoopGroup work = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(work).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {

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
										public void channelActive(ChannelHandlerContext ctx)
												throws Exception {
											String msg = "msg from client" + System.lineSeparator();
											for (int i = 0; i < 1000; i++) {
												ctx.writeAndFlush(msg);
											}
										}

										@Override
										public void channelRead(ChannelHandlerContext ctx,
												Object msg) throws Exception {
											System.out.println(
													"client收到第" + (++count) + "次消息：" + msg);
										}
									});
						}
					});

			ChannelFuture connect = b.connect("localhost", 8080);
			connect.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			work.shutdownGracefully();
		}
	}
}
