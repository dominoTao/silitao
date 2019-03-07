package com.liangtao.core.concurrency;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 当你需要完成任务时返回一个值，则用Callable对象，调用call()方法
 * Executor调用的方法是submit而不是execute方法
 * submit方法会产生Future对象，它用Callable返回的特定类型进行了参数化
 * 可以用isDone方法来查看Future是否已经完成
 * 
 */
public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();
		for(int i = 0 ; i < 5 ; i++) {
			results.add(exec.submit(new TaskWithResult(i)));
		}
		for(Future<String> fs : results) {
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				System.out.println(e);
				return;
			} catch (ExecutionException e) {
				System.out.println(e);
			} finally {
				exec.shutdown();
			}
		}
	}
}
