package com.liangtao.core.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncObject {
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread() {
			public void run() {
				ds.f();
			}
		}.start();
		ds.g();
	}
}
class DualSynch {
	private Object syncObject = new Object();
	public synchronized void f() {
		for(int i = 0; i < 5 ; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}
	public void g() {
		Lock lock = new ReentrantLock();
//		synchronized (syncObject) {
		lock.lock();
		try {
			for(int i = 0; i < 5 ; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		} finally {
			lock.unlock();
		}
//		}
	}
}