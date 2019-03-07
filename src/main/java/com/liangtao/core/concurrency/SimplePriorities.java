package com.liangtao.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {
	private int countDown = 5;
	private volatile double d;//努力确保不进行任何编译器优化0
	private int priority;
	
	public SimplePriorities(int priority) {
		super();
		this.priority = priority;
	}

	@Override
	public String toString() {
		return Thread.currentThread()+"\t"+countDown;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);
		while(true) {
			//一个昂贵的可中断的操作
			for(int i = 1 ; i< 100000 ; i++) {
				d += (Math.PI + Math.E) / (double)i;
				if(i % 1000 == 0) {
					Thread.yield();
				}
			}
			System.out.println(this);
			if(--countDown == 0) {
				return;
			}
		}
	}
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0 ; i < 5 ; i++) {
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
		}
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}
}
