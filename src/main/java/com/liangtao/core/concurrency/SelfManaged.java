package com.liangtao.core.concurrency;

/**
 * start()是在构造其中调用
 * 构造器中启动线程可能会变得很有问题
 * 因为另一个任务可能会在构造器结束之前开始执行，这意味着该任务能够访问处于不稳定状态的对象
 * 优选Executor而不是显式地创建Thread对象的另一个原因
 */
public class SelfManaged implements Runnable {
	private int countDown = 5;
	private Thread t = new Thread(this);
	public SelfManaged() {
		t.start();
	}
	public String toString() {
		return Thread.currentThread().getName()+"(" + countDown + "), ";
	}
	public void run() {
		while(true) {
			System.out.println(this);
			if(--countDown == 0)
				return;
		}
	}
	public static void main(String[] args) {
		for(int i = 0 ; i < 5; i++) {
			new SelfManaged();
		}
	}

}
