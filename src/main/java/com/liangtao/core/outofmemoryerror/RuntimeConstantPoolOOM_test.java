package com.liangtao.core.outofmemoryerror;

/**
 * String.intern()返回引用的测试
 * 这段代码在jdk1.6中运行，会得到两个false，在jdk1.7中运行，会得到一个true和一个false
 * 原因：
 * 在jdk1.6中，intern()方法会把首次遇到的字符串实例复制到永久代中，返回的也是永久代中这个字符串实例的引用
 * 而由StringBuilder创建的字符串实例在java堆上，所以必然不是同一个引用
 * 
 * 在jdk1.7中，intern()方法不再复制实例，只是在常量池中记录首次出现的实例引用，因此intern()返回的引用和由StringBuilder创建的那个字符串实例是同一个
 * 对str2比较返回false是因为java这个字符串在执行StringBuilder.toString()之前已经出现过，字符串常量池中已经有它的引用了，不符合首次出现的原则，而计算机软件这个字符串则是首次出现的，因此返回true
 * @author Administrator
 *
 */
public class RuntimeConstantPoolOOM_test {
	public static void main(String[] args) {
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
	}
}
