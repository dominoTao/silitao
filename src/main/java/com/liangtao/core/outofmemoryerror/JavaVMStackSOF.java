package com.liangtao.core.outofmemoryerror;

/**
 * 虚拟机栈和本地方法栈OOM测试
 * VM Args: -verbose:gc -Xms20M -Xmx20M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -Xss128k
 * @author Administrator
 *
 */
public class JavaVMStackSOF {
	private int stackLength = 1;
	
	public void stackLeak() {
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args)throws Throwable {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("stack length : " + oom.stackLength);
			throw e;
		}
	}
}
