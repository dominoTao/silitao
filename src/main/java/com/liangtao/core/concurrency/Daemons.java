package com.liangtao.core.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Daemon线程被设置成了后台模式，然后派生出许多子线程
 * 这些线程并没有被显式地设置为后台模式，不过他们确实是后台线程
 */
public class Daemons {
	public static void main(String[] args) throws InterruptedException {
		Thread d = new Thread(new Daemon());
		d.setDaemon(true);
		d.start();
		System.out.println("d.isDaemon() = "+d.isDaemon()+", ");
		TimeUnit.SECONDS.sleep(1);
	}
}
class Daemon implements Runnable {

	private Thread[] t = new Thread[10];
	public void run() {
		for(int i = 0 ; i < t.length ; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.println("DaemonSpawn " + i + " started, ");
		}
		for(int i = 0 ; i < t.length ; i++) {
			System.out.println("t["+i+"].isDaemon() = "+t[i].isDaemon()+", ");
		}
		while(true)
			Thread.yield();
	}
	
}
class DaemonSpawn implements Runnable {

	public void run() {
		while(true)
			Thread.yield();
	}
	
}