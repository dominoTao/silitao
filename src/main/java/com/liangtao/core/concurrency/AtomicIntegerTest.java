package com.liangtao.core.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类
 * 
 * 在机器级别上可保证原子性的,进行AtomicityTest.java重写
 * java.util.concurrent.atomic包中的类均可以保证机器级别上的原子性，别设计用来构建java.util.concurrent中的类
 * 因此在特殊情况下才在自己的代码中只用它们
 * AtomicInteger消除了synchronized关键字
 */
public class AtomicIntegerTest implements Runnable {
	private AtomicInteger i = new AtomicInteger(0);
	
	private void evenIncrement(){
		i.addAndGet(2);
	}
	
	private int getValue(){
		return i.get();
	}
	
	public void run() {
		while(true) {
			evenIncrement();
		}
	}
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			public void run() {
				System.out.println("Aborting");
				System.exit(0);
			}
		}, 5000);//5秒后终止，防止线程一直执行任务
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicIntegerTest ait = new AtomicIntegerTest();
		exec.execute(ait);
		while(true) {
			int val = ait.getValue();
			if(val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}
