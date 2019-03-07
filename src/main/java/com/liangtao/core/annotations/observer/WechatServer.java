package com.liangtao.core.annotations.observer;

import java.util.ArrayList;
import java.util.List;
/**
 * 被观察者，也就是微信公众号服务
 * 实现了Observerable接口，对Observerable接口的三个方法进行了具体实现
 * 同时有一个List集合，用以保存注册的观察者，等需要通知观察者时，遍历该集合即可。
 */
public class WechatServer implements Observerable {

	private List<Observer> list;
	
	private String message;
	
	
	
	public WechatServer(List<Observer> list) {
		super();
		this.list = new ArrayList<Observer>();
	}

	@Override
	public void notifyObserver() {
		for(Observer o : list) {
			o.update(message);
		}
	}

	@Override
	public void registerObserver(Observer o) {
		list.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		if(!list.isEmpty()) {
			list.remove(o);
		}
		
	}
	
	public void setInfomation(String s) {
        this.message = s;
        System.out.println("微信服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }
}
