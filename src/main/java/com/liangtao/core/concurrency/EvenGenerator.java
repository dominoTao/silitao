package com.liangtao.core.concurrency;

/**
 * 共享资源之 不正确访问资源
 */

public class EvenGenerator extends IntGenerator {
	private int currentEvenValue = 0;
	@Override
	public int next() {
		++currentEvenValue;
		++currentEvenValue;
		return currentEvenValue;
	};
	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}
	/**
	 * 
Press Control-c to exit
107 not even!
113 not even!
109 not even!
111 not even!

	 */
}
