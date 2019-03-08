package com.liangtao.core.concurrency;

/**
 * UnresponsiveUi在一个无限的while循环里执行运算，显然程序不能到达读取控制台输入的那一行
 * (编译器被欺骗了，相信while的条件使得程序能到达读取控制台输入的哪一行)
 * 
 * 要想让程序有响应，就得把计算程序放到run()方法中，这样他就能让出处理器给别的程序
 */
public class ResponsiveUi extends Thread {
	private static volatile double d = 1;
	public ResponsiveUi() {
		setDaemon(true);
		start();
	}
	public void run() {
		while(true)
			d = d + (Math.PI + Math.E) / d;
	}
	public static void main(String[] args) throws Exception {
//		new UnresponsiveUi(); //must kill this process
		new ResponsiveUi();
		System.in.read();
		System.out.println(d);//show progress
	}
}
class UnresponsiveUi {
	private volatile double d = 1;
	public UnresponsiveUi()throws Exception {
		while(d > 0)
			d = d + (Math.PI + Math.E) / d;
		System.in.read(); //never gets here
	}
}