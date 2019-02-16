package com.liangtao.core.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 构造数组的MaxTree
 * 
 * 一个数组的MaxTree定义如下：
 * 数组必须没有重复元素
 * MaxTree是一颗二叉树，数组的每一个值对应一个二叉树节点
 * 包括MaxTree树在内且在其中的每一颗子树上，值最大的节点都是树的头
 * @author Administrator
 *
 */
public class MaxTree {
	public Node getMaxTree(int[] arr) {
		Node[] nArr = new Node[arr.length];
		for(int i = 0; i != arr.length; i++) {
			nArr[i] = new Node(arr[i]);
		}
		return null;
	}
	
	public void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map) {
		Node popNode = stack.pop();
		if(stack.isEmpty()) {
			map.put(popNode, null);
		}else {
			map.put(popNode, stack.peek());
		}
	}
}
class Node{
	public int value;
	public Node left;
	public Node right;
	public Node(int data) {
		this.value = data;
	}
}