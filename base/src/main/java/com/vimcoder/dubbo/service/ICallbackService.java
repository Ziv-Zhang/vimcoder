package com.vimcoder.dubbo.service;

public interface ICallbackService {
	void addListener(String key, ICallbackListener listener);
}
