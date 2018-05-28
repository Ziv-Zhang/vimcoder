package com.vimcoder.netty;

import io.netty.bootstrap.Bootstrap;
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
import io.netty.channel.socket.nio.NioSocketChannel;

public class SimpleNettyClient {
	public void connect(int port, String host) {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).
			option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel arg0) throws Exception {
					arg0.pipeline().addLast(new ChannelHandlerAdapter() {
						@Override
						public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
							ByteBuf buf = (ByteBuf) msg;
							byte[] read = new byte[buf.readableBytes()];
							buf.readBytes(read);
							System.out.println(new String(read));
						}
						
						@Override
						public void channelActive(ChannelHandlerContext ctx) throws Exception {
							ctx.writeAndFlush(Unpooled.copiedBuffer("client send request".getBytes()));
						}
						
						@Override
						public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
							cause.printStackTrace();
						}
					});
				}
			});
			
			ChannelFuture f =b.connect(host, port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		new SimpleNettyClient().connect(8080, "localhost");
	}
}
