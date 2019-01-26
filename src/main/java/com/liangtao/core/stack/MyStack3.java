package com.liangtao.core.stack;

import java.util.Stack;

/**
 * 两个栈实现队列
 * @author Administrator
 *
 */
public class MyStack3 {
	private Stack<Integer> stackPush;
	private Stack<Integer> stackPop;
	public MyStack3() {
		this.stackPush = new Stack<Integer>();
		this.stackPop = new Stack<Integer>();
	}
	/**
	 * 入队列
	 * @param newNum
	 */
	public void push(int newNum) {
		stackPush.push(newNum);
	}
	/**
	 * 出队列
	 * @return
	 */
	public int pop() {
		if(stackPush.isEmpty() && stackPop.isEmpty()) {
			throw new RuntimeException("队列空！");
		}else if(stackPop.isEmpty()) {
			while(!stackPush.isEmpty()) {
				stackPop.push(stackPush.pop());
			}
		}
		return stackPop.pop();
	}
	/**
	 * 获取队列第一个元素
	 * @return
	 */
	public int peek() {
		if(stackPush.isEmpty() && stackPop.isEmpty()) {
			throw new RuntimeException("队列空！");
		}else if(stackPop.isEmpty()) {
			while(!stackPush.isEmpty()) {
				stackPop.push(stackPush.pop());
			}
		}
		return stackPop.peek();
	}
	
	public static void main(String[] args) {
		MyStack3 test = new MyStack3();
		test.stackPush.add(1);
		test.stackPush.add(2);
		test.stackPush.add(3);
		test.stackPush.add(4);
		test.stackPush.add(5);
		int count = test.stackPop.size()+test.stackPush.size();
		for(int i = 0 ; i < count; i++) {
			System.out.println(test.pop());
		}
	}
}
