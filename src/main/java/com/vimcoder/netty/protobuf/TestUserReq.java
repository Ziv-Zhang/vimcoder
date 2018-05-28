package com.vimcoder.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.vimcoder.netty.protobuf.UserReqProto.UserReq;
import com.vimcoder.netty.protobuf.UserReqProto.UserReq.Builder;

public class TestUserReq {

	private static UserReq newInstance() {
		Builder builder = UserReq.newBuilder();
		builder.setId(1);
		builder.setName("ziv");
		builder.setAddr("wx");

		return builder.build();
	}

	private static byte[] encode(UserReq userReq) {
		return userReq.toByteArray();
	}

	private static UserReq decode(byte[] bytes) {
		try {
			return UserReq.parseFrom(bytes);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		UserReq req = newInstance();
		System.out.println(req.getId());
		System.out.println(req.getName());
		System.out.println(req.getAddr());
		byte[] b = encode(req);
		System.out.println(b);
		req = decode(b);
		System.out.println(req.getId());
		System.out.println(req.getName());
		System.out.println(req.getAddr());
	}
}
