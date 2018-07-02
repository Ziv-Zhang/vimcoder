package com.vimcoder.netty.messagepack;

import java.util.List;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MessagePackDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		final int length = in.readableBytes();
		final byte[] inBytes = new byte[length];
		// in.getBytes(in.readerIndex(), inBytes, 0, length);
		in.readBytes(inBytes);
		MessagePack msgPack = new MessagePack();
		out.add(msgPack.read(inBytes));
	}

}
