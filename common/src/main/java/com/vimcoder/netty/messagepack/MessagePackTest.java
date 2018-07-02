package com.vimcoder.netty.messagepack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

public class MessagePackTest {
	public static void main(String[] args) throws IOException {
		List<String> l = new ArrayList<>( Arrays.asList( "11", "22", "33" ) );
		MessagePack msgPack = new MessagePack();
		// serialize
		byte[] bytes = msgPack.write( l );
		System.out.println( Arrays.toString( bytes ) );
		List<String> rest = msgPack.read( bytes, Templates.tList( Templates.TString ) );
		System.out.println( rest );
	}
}
