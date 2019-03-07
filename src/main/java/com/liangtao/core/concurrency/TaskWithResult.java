package com.liangtao.core.concurrency;

import java.util.concurrent.Callable;

public class TaskWithResult implements Callable<String> {
	private int id;
	
	public TaskWithResult(int id) {
		super();
		this.id = id;
	}
	
	@Override
	public String call() throws Exception {
		return "result of TaskWithResult "+id;
	}
}