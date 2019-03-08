package com.liangtao.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Executors.newCachedThreadPool()方法接收一个线程工厂对象（重载），生成用来生成新线程的对象
 */
public class DaemonFromFactory implements Runnable {

	@Override
	public void run() {
		try {
			while(true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread()+ " " +this);
			}
		} catch (Exception e) {
			System.out.println("Interrupted");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i = 0 ; i < 10 ; i++) {
			exec.execute(new DaemonFromFactory());
		}
		System.out.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(500);
	}
}
