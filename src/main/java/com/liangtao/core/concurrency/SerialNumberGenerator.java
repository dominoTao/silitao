package com.liangtao.core.concurrency;

/**
 * 当nextSerialNumber()被调用的时候，他必须向调用者返回唯一值
 * 
 * 这里的问题是 nextSerialNumber()在没有同步的情况下对共享资源进行了访问
 */
public class SerialNumberGenerator {
	/**
	 * 如果一个域可能被多个任务同时访问，或者这些任务中至少有一个写入任务，就应该为这个域设置为volatile
	 * 
	 * 如果你将一个域定义为volatile，那么他就会告诉编译器不要执行任何移除读取和写入操作的优化，这些操作的目的是用线程中局部变量维护对这个域的同步
	 * 
	 * 读取和写入都是直接针对内存的，却并没有被缓存
	 * 
	 * volatile并不能对递增不是原子性操作这一事实产生影响
	 */
	private static volatile int serialNumber = 0;
	public synchronized static int nextSerialNumber() {
//	public static int nextSerialNumber() {
		return serialNumber++;
	}
}
