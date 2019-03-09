package com.liangtao.core.concurrency;

/**
 * 同步控制EvenGenerator
 */
public class SynchronizedEvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	
	@Override
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
	}
	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}
