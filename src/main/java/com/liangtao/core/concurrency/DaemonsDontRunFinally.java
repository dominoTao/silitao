package com.liangtao.core.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * finally子句并且有执行
 * 如果注释掉t.setDaemon(true);则会看到finally子句别执行
 * 
 * 当最后一个非后台线程终止时，后台线程会“突然”终止
 * 一旦main（）退出，jvm会立即关闭所有后台线程
 */
class ADaemon implements Runnable {

	public void run() {
		try {
			System.out.println("Starting ADaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			System.out.println("Exiting via InterruptedExecption");
		} finally {
			System.out.println("This should always run?");
		}
	}

}
public class DaemonsDontRunFinally{
	public static void main(String[] args) {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);
		t.start();
	}
}