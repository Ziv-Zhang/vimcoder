package com.vimcoder.dubbo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.springframework.stereotype.Service;

import com.vimcoder.dubbo.service.ICallbackListener;
import com.vimcoder.dubbo.service.ICallbackService;

@Service
public class CallbackService implements ICallbackService {

	private final Map<String, ICallbackListener> listeners = new ConcurrentHashMap<>();

	public CallbackService() {
//		Thread t = new Thread(new Runnable() {
//			public void run() {
//				while(true){
//					try{
//						for(Map.Entry<String, ICallbackListener> entry : listeners.entrySet()){
//							try{
//								entry.getValue().changed(getChanged(entry.getKey()));
//							}catch(Throwable t){
//								listeners.remove(entry.getKey());
//							}
//						}
//						Thread.sleep(5000); // 定时触发变更通知
//					}catch(Throwable t){ // 防御容错
//						t.printStackTrace();
//					}
//				}
//			}
//		});
//		t.setDaemon(true);
//		t.start();
	}

	@Override
	public void addListener(String key, ICallbackListener listener) {
		listeners.put(key, listener);
		listener.changed(getChanged(key));
		System.out.println("CallbackService invoked");
	}

	private String getChanged(String key) {
		return key + " Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}
