package com.liangtao.core.concurrency;

/**
 * 加入一个线程
 * 
 * 如果某个线程在另一个线程t上调用t.join()，此线程将被挂起，直到目标线程t结束才恢复（即t.isAlive()返回为假）
 * 在调用join()方法的时候带上一个超时参数，如果目标线程在这段时间到期时还没有结束的话，join()方法总能返回
 * join()方法的调用可以被中断，做法是在调用线程上调用interrupt()方法，这时需要调用try-catch子句
 */
class Sleeper extends Thread {
	private int duration;

	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();
	}
	
	public void run() {
		try {
			sleep(duration);
		} catch (Exception e) {
			System.out.println(getName()+"was interrupted.isInterrupted():  "+isInterrupted() );
			return;
		}
		System.out.println(getName()+" has awakened");
	}
}
class Joiner extends Thread {
	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}
	public void run() {
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		System.out.println(getName() + " join completed");
	}
}
public class Joining {
	public static void main(String[] args) {
		Sleeper sleepy = new Sleeper("Sleepy", 1500),grumpy = new Sleeper("Grumpy", 1500);
		Joiner dopey = new Joiner("Dopey", sleepy), doc = new Joiner("Doc", grumpy);
		grumpy.interrupt();
		
	}
}