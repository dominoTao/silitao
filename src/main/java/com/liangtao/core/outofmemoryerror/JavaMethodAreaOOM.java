package com.liangtao.core.outofmemoryerror;

/**
 * 借助CGLib使方法区出现内存溢出异常(CGLib直接操作字节码运行时生成了大量的动态类)
 * https://github.com/cglib/cglib
 * VM Args: -verbose:gc -Xms20M -Xmx20M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author Administrator
 *
 */
public class JavaMethodAreaOOM {
	public static void main(String[] args) {
		while(true) {
//			Enhancer enhancer = new Enhancer();
		}
	}
	static class OOMObject{}
}
