package com.liangtao.core.concurrency;
import java.util.concurrent.*;

/**
 * 该类包含一个静态的CircularSet，另外还包含一个内嵌的SerialChecker，可以确保序列数是唯一的
 * 
 * 通过创建多个任务来竞争序列数，你会发现这些任务最终会得到重复的序列数
 * 
 * 在SerialNumberGenerator.nextSerialNumber()前加synchronized关键字可以解决
 */
public class SerialNumberChecker {
	private static final int SIZE = 10;
	private static CircularSet serials = new CircularSet(1000);
	private static ExecutorService exec = Executors.newCachedThreadPool();
	static class SerialChecker implements Runnable {
		public void run() {
			while(true) {
				int serial = SerialNumberGenerator.nextSerialNumber();
				if(serials.contains(serial)) {
					System.out.println("重复 : " + serial);
					System.exit(0);
				}
				serials.add(serial);
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		for(int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker());
		}
		if(args.length>0) {
			TimeUnit.SECONDS.sleep(new Integer(args[0]));
			System.out.println("没有重复检查");
			System.exit(0);
		}
	}
}
class CircularSet{
	private int[] array;
	private int len;
	private int index = 0;
	public CircularSet(int size) {
		super();
		array = new int[size];
		len = size;
		for(int i = 0 ; i < size ; i++) {
			array[i] = -1;
		}
	}
	public synchronized void add(int i) {
		array[index] = i;
		index = ++index % len;
	}
	public synchronized boolean contains(int val) {
		for(int i = 0; i < len; i++) {
			if(array[i] == val) {
				return true;
			}
		}
		return false;
	}
}