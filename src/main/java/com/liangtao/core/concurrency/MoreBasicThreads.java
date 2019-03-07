package com.liangtao.core.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MoreBasicThreads {
	public static void main(String[] args) {
//		for(int i = 0 ; i < 5 ; i++) {
//			new Thread(new LiftOff()).start();;
//		}
//		System.out.println("Waiting for LiftOff");
//		ExecutorService exec = Executors.newCachedThreadPool();//为每个任务都创建一个线程
		ExecutorService exec = Executors.newFixedThreadPool(5);//用有限的线程集来执行提交的任务
		for(int i = 0 ; i < 5 ; i++)
			exec.execute(new LiftOff());
		exec.shutdown();
	}
}
