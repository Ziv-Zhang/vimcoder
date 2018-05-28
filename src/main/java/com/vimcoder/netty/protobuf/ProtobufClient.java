package com.vimcoder.netty.protobuf;

import com.vimcoder.netty.protobuf.UserReqProto.UserReq;
import com.vimcoder.netty.protobuf.UserReqProto.UserReq.Builder;
import com.vimcoder.netty.protobuf.UserRespProto.UserResp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtobufClient {
	public static void main(String[] args) {
		NioEventLoopGroup work = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(work).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline()
									.addLast(new ProtobufVarint32FrameDecoder())
									.addLast(new ProtobufDecoder(UserResp.getDefaultInstance()))
									.addLast(new ProtobufVarint32LengthFieldPrepender())
									.addLast(new ProtobufEncoder())
									.addLast(new ChannelHandlerAdapter() {

										int count = 0;

										@Override
										public void channelActive(ChannelHandlerContext ctx)
												throws Exception {
											String msg = "msg from client";
											for (int i = 0; i < 1000; i++) {
												Builder builder = UserReq.newBuilder();
												builder.setAddr(msg);
												builder.setId(i);
												builder.setName("ziv");
												ctx.writeAndFlush(builder.build());
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
