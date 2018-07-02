package com.vimcoder.netty;

import java.awt.Event;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SimpleNettyServer {
	public void bind(int port) {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup work = new NioEventLoopGroup();
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(boss, work).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel arg0) throws Exception {
							arg0.pipeline().addLast(new ChannelHandlerAdapter() {
								@Override
								public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
									ByteBuf buf = (ByteBuf) msg;
									byte[] req = new byte[buf.readableBytes()];
									buf.readBytes(req);
									String body = new String(req);
									System.out.println("read msg ：" + body);
									ctx.write(Unpooled.copiedBuffer("response".getBytes()));
								}

								@Override
								public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
										throws Exception {
									cause.printStackTrace();
								}

								@Override
								public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
									ctx.flush();
								}

							});
						}
					});

			ChannelFuture f = server.bind(port).sync();
			f.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			work.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		new SimpleNettyServer().bind(8080);
	}
}
