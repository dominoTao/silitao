package com.liangtao.core.outofmemoryerror;

/**
 * 创建线程导致内存溢出异常
 * VM Args: -verbose:gc -Xms20M -Xmx20M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -Xss2M
 * @author Administrator
 *
 */
public class JavaVMStackOOM {
	private void dontStop() {
		while(true) {
			
		}
	}
	public void stackLeakByThread() {
		while(true) {
			Thread thread = new Thread(new Runnable(){

				public void run() {
					// TODO Auto-generated method stub
					dontStop();
				}
				
			});
			thread.start();
		}
	}
	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
}
