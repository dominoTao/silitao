package com.yitong.core;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现栈的基础上，再实现返回栈中最小元素的操作
 * pop,push,getMin操作的时间复杂度都为O(1)
 * 设计的栈类型可以使用现成的栈结构
 * @author Administrator
 */
public class MyStack1 {
	private Stack<Integer> stackData;
	private Stack<Integer> stackMin;
	
	public MyStack1(Stack<Integer> stackData, Stack<Integer> stackMin) {
		super();
		this.stackData = stackData;
		this.stackMin = stackMin;
	}
	
	public void push(int newNum) {
		if(this.stackMin.isEmpty()) {
			this.stackMin.push(newNum);
		}else if (newNum <= this.getMin()) {
			this.stackMin.push(newNum);
		}
		this.stackData.push(newNum);
	}
	
	public int pop(int newNum) {
		if(this.stackData.isEmpty()) {
			throw new RuntimeException("Your stack is empty.");
		}
		int value = this.stackData.pop();
		if (value == this.getMin())
			this.stackMin.pop();
		return value;
	}
	
	public int getMin() {
		if(this.stackMin.isEmpty()) 
			throw new RuntimeException("Your stack is empty.");
		return this.stackMin.peek();
	}
	
}
