package com.vimcoder.netty.protobuf;

import com.vimcoder.netty.protobuf.UserReqProto.UserReq;
import com.vimcoder.netty.protobuf.UserRespProto.UserResp;
import com.vimcoder.netty.protobuf.UserRespProto.UserResp.Builder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ProtobufServer {
	public static void main(String[] args) {
		NioEventLoopGroup boss = new NioEventLoopGroup();
		NioEventLoopGroup work = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(boss, work).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline()
									.addLast(new ProtobufVarint32FrameDecoder())
									.addLast(new ProtobufDecoder(UserReq.getDefaultInstance()))
									.addLast(new ProtobufVarint32LengthFieldPrepender())
									.addLast(new ProtobufEncoder())
									.addLast(new ChannelHandlerAdapter() {
										int count = 0;

										@Override
										public void channelRead(ChannelHandlerContext ctx,
												Object msg) throws Exception {
											System.out.println(
													"server 收到第 " + (++count) + " 次消息：" + msg);
											
											Builder resp = UserResp.newBuilder();
											resp.setCode("200");
											resp.setDesc("server response");
											ctx.writeAndFlush(resp.build());
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
