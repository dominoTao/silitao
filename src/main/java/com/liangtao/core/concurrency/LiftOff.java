package com.liangtao.core.concurrency;

/**
 *	电梯
 */
public class LiftOff implements Runnable {
	protected int countDown = 10;//倒计时
	private static int taskCount = 0;//任务数
	private final int id = taskCount++;
	public LiftOff() {
		
	}
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	
	public String status() {
		return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!")+"),";
	}
	@Override
	public void run() {
		while(countDown-- > 0) {
			System.out.print(status());
			Thread.yield();
		}
	}

}
