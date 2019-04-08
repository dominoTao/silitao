package com.liangtao.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 找到奇数时停止
 * 
 * 对基本类型的读取和赋值操作被认为是安全的原子性操作，但是
 * 当对象处于不稳定状态的时候，仍旧很有可能使用原子操作来访问它们
 * 
 */
public class AtomicityTest implements Runnable {
	/**
	 * i不是volatile的，因此还存在可视性问题
	 * 如果一个域可能被多个任务同时访问，或者这些任务中至少有一个写入任务，就应该为这个域设置为volatile
	 */
//	private int i = 0;
	
	private static volatile int i = 0;
	private synchronized void evenIncrement(){
		i++;
		i++;
	}
	/**
	 * return i;确实是原子性的，但是缺少同步使其数值可以在处于不稳定的中间状态时被读取
	 * i也不是volatile的，因此还存在可视性问题
	 */
//	private int getValue(){
	private synchronized int getValue(){
		return i;
	}
	public void run() {
		while(true)
			evenIncrement();
	}
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		exec.execute(at);
		while(true) {
			int val = at.getValue();
			if(val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}
}
