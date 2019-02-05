package com.liangtao.core.stack;

import java.util.Stack;

/**
 * 用一个栈实现另一个栈的排序
 * 
 * @author tao
 * 将要排序的栈记为stack，申请的辅助栈记为help。在stack上执行pop操作，弹出的元素记为cur
 * 如果cur小于等于help的栈顶元素，则将cur直接压入help
 * 如果cur大于help的栈顶元素，则将help的元素逐一弹出，逐一压入stack，直到cur小于等于help的栈顶元素，再将cur压入help
 */
public class StackSort {
	/**
	 * 栈顶元素大于栈底元素
	 * @param stack
	 */
	public static void sortStackByStack(Stack<Integer> stack) {
		Stack<Integer> help = new Stack<Integer>();
		
		while(!stack.isEmpty()) {
			int cur = stack.pop();
			while(!help.isEmpty() && help.peek() < cur) {
				stack.push(help.pop());
			}
			help.push(cur);
		}
		
		while(!help.isEmpty()) {
			stack.push(help.pop());
		}
	}
	
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(2);
		stack.push(132);
		stack.push(23);
		stack.push(1);
		stack.push(55);
		stack.push(345);
		
		sortStackByStack(stack);
		
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + "\t");
		}
	}
}
