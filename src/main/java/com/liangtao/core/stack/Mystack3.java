package com.liangtao.core.stack;

import java.util.Iterator;
import java.util.Stack;

public class Mystack3 {
	public Stack<Integer> stackPush;
	public Stack<Integer> stackPop;
	public Mystack3(){
		stackPush = new Stack<Integer>();
		stackPop = new Stack<Integer>();
	}
	public void add (int pushInt){
		stackPush.push(pushInt);
	}
	public int poll(){
		if(stackPush.empty() && stackPop.empty()){
			throw new RuntimeException("队列为空");
		}else if(stackPop.empty()){
			while(!stackPush.empty()){
				stackPop.push(stackPush.pop());
			}
		}
		return stackPop.pop();
		
	}
	
	public int peek(){
		if(stackPop.empty() && stackPush.empty()){
			throw new RuntimeException("队列为空");
			
		}else if(stackPop.empty()){
			while(!stackPush.empty()){
				stackPop.push(stackPush.pop());
			}
		}
		return stackPop.peek();
	}	
	
	public static void main(String[] args) {
		Mystack3 my3 = new Mystack3();
		my3.stackPush.add(1);
		my3.stackPush.add(2);
		my3.stackPush.add(3);
		my3.stackPush.add(4);
		my3.stackPush.add(5);
		my3.peek();
		int count = my3.stackPop.size();
		for(int i = 0 ; i < count; i++) {
			System.out.println(my3.stackPop.pop());
		}
	}
}
