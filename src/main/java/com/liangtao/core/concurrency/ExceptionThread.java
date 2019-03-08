package com.liangtao.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 捕获异常
 */
public class ExceptionThread implements Runnable {

	@Override
	public void run() {
		throw new RuntimeException();
	}
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}
/**
 * 未捕获的异常
 * Exception in thread "pool-1-thread-1" java.lang.RuntimeException
	at com.liangtao.core.concurrency.ExceptionThread.run(ExceptionThread.java:10)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)

 */
}
