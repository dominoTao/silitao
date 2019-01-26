package com.liangtao.core.stack;

import java.util.Stack;

/**
 * 查询当前操作数栈中的最小值操作
 * @author Administrator
 */
public class MyStack2 {
	private Stack<Integer> stackData;
	private Stack<Integer> stackMin;
	
	public MyStack2() {
		this.stackData = new Stack<Integer>();
		this.stackMin = new Stack<Integer>();
	}
	public void push(int newNum) {
		if(this.stackMin.isEmpty()) {
			this.stackMin.push(newNum);
		}else if(newNum < this.getMin()){
			this.stackMin.push(newNum);
		}else{
			int newMin = this.stackMin.peek();
			this.stackMin.push(newMin);
		}
		this.stackData.push(newNum);
	}
	public int pop() {
		if(this.stackData.isEmpty()){
			throw new RuntimeException("Your stack is empty.");
		}
		this.stackMin.pop();
		return this.stackData.pop();
	}
	public int getMin() {
		if(this.stackMin.isEmpty()) {
			throw new RuntimeException("Your stack is empty.");
		}
		return this.stackMin.peek();
	}
}

