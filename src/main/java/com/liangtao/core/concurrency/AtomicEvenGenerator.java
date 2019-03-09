package com.liangtao.core.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类
 * 
 * 重写MutexEvenGenerator.java
 * Atomic classes 在常规代码中偶尔有用
 */
public class AtomicEvenGenerator extends IntGenerator {

	private AtomicInteger currentEvenValue = new AtomicInteger(0);
	
	@Override
	public int next() {
		
		return currentEvenValue.addAndGet(2);
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new AtomicEvenGenerator());
	}

}
