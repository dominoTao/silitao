package com.liangtao.core.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock对象比内建的synchronized锁具有更细粒度的控制
 */
public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();
	/**
	 * 不计时尝试获取锁
	 */
	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock() : " + captured);
		} finally {
			if(captured)
				lock.unlock();
		}
	}
	/**
	 * 计时尝试获取锁
	 */
	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
		try {
			System.out.println("tryLock(2, TimeUnit.SECONDE) : "+captured);
		} finally {
			if(captured)
				lock.unlock();
		}
	}
	public static void main(String[] args) {
		final AttemptLocking al = new AttemptLocking();
		al.untimed();
		al.timed();
		
		new Thread() {
			{setDaemon(true);}//什么结构
			public void run() {
				al.lock.lock();
				System.out.println("acquired");
			}
		}.start();
		Thread.yield();
		al.untimed();
		al.timed();
	}
}
