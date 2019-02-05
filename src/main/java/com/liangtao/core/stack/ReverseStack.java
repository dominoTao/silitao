package com.liangtao.core.stack;

import java.util.Stack;

/**
 * 实现栈中元素的逆序
 * 
 * 
 * 一个栈依次压入1,2,3,4,5，那么从栈顶到栈底分别为5,4,3,2,1。
 * 将这个栈 转置后，从栈顶到栈底为1,2,3,4,5,也就是实现栈中元素的逆序
 * 只能用递归函数来实现，不能用其他数据结构
 * @author tao
 */
public class ReverseStack {
	/**
	 * 获取栈底元素，并删除
	 * @param stack
	 * @return
	 */
	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		int result = stack.pop();
		if(stack.isEmpty()) {
			return result;
		}else {
			//栈顶元素与栈顶元素的下一个元素交换位置
			int last = getAndRemoveLastElement(stack);
			stack.push(result);
			return last;
		}
	}
	
	/**
	 * 逆序栈
	 * @param stack
	 */
	public static void reverse(Stack<Integer> stack) {
		if(stack.isEmpty()) {
			return ;
		}
		int i = getAndRemoveLastElement(stack);
		reverse(stack);
		stack.push(i);
	}
	
	
	
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		
//		while(!stack.isEmpty()) {
//			System.out.print(stack.pop()+"\t");
//		}
		
		reverse(stack);
		
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+"\t");
		}
	}
	
}
