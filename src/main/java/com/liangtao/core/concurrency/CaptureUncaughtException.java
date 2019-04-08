package com.liangtao.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * ExceptionThread中未捕获的异常问题的解决方案：修改Executor产生线程的方式。
 * Thread.getUncaughtExceptionHandler()允许在每个Thread对象上都附着一个异常处理器
 */
public class CaptureUncaughtException {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
	}
}
class ExceptionThread2 implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		Thread t = Thread.currentThread();
		System.out.println("run() by "+t);
		System.out.println("eh = "+t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
	/**
com.liangtao.core.concurrency.HandlerThreadFactory@70dea4e creating new Thread
created Thread[Thread-0,5,main]
eh = com.liangtao.core.concurrency.MyUncaughtExceptionHandler@5c647e05
run() by Thread[Thread-0,5,main]
eh = com.liangtao.core.concurrency.MyUncaughtExceptionHandler@5c647e05
com.liangtao.core.concurrency.HandlerThreadFactory@70dea4e creating new Thread
created Thread[Thread-1,5,main]
eh = com.liangtao.core.concurrency.MyUncaughtExceptionHandler@3986e8ee
caught java.lang.RuntimeException
	 */
}
/**
 * Thread.UncaughtExceptionHandler.uncaughtException()会在线程因未捕获的异常而临近死亡时被调用
 */
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught "+e);
	}
}
class HandlerThreadFactory implements ThreadFactory {

	public Thread newThread(Runnable r) {
		System.out.println(this + " creating new Thread");
		Thread t = new Thread(r);
		System.out.println("created "+t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh = "+t.getUncaughtExceptionHandler());
		return t;
	}
	
}