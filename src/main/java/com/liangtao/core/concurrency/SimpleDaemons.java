package com.liangtao.core.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 后台进程
 */
public class SimpleDaemons implements Runnable {
	public void run() {
		try {
			while(true){
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread()+" "+this);
			}
		} catch (Exception e) {
			System.out.println("sleep() interrupted");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		for(int i = 0 ; i < 10 ; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);
			daemon.start();
		}
		System.out.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(175);
	}
}
