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
	public static void main(String[] args) {
		MaxTree mt = new MaxTree();
		int[] arr = {2,3,4,1,6,55,34,23,12,123,1233};
		System.out.println(mt.getMaxTree(arr).value);
		System.out.println(mt.getMaxTree(arr).left.value);
		System.out.println(mt.getMaxTree(arr).right.value);
	}
	public Node getMaxTree(int[] arr) {
		//创建一个arr数组相同长度的Node类型的数组
		Node[] nArr = new Node[arr.length];
		//将arr中的值依次存入nArr数组中
		for(int i = 0; i != arr.length; i++) {
			nArr[i] = new Node(arr[i]);
		}
		Stack<Node> stack = new Stack<Node>();
		HashMap<Node, Node> lBigMap = new HashMap<Node, Node>();
		HashMap<Node, Node> rBigMap = new HashMap<Node, Node>();
		//看不懂的可以看看StackSort类
		//lBigMap中完成存储value是key左侧第一个比它大的元素
		for(int i = 0; i != nArr.length; i++) {
			Node curNode = nArr[i];
			while((!stack.isEmpty()) && stack.peek().value < curNode.value) {
				popStackSetMap(stack, lBigMap);
			}
			stack.push(curNode);
		}
		while(!stack.isEmpty()) {
			popStackSetMap(stack, lBigMap);
		}
		//rBigMap中完成存储value是key右侧第一个比它大的元素
		for(int i = nArr.length - 1; i != -1; i--) {
			Node curNode = nArr[i];
			while((!stack.isEmpty()) && stack.peek().value < curNode.value) {
				popStackSetMap(stack, rBigMap);
			}
			stack.push(curNode);
		}
		while(!stack.isEmpty()) {
			popStackSetMap(stack, rBigMap);
		}
		Node head = null;
		for(int i = 0; i != nArr.length; i++) {
			Node curNode = nArr[i];
			Node left = lBigMap.get(curNode);
			Node right = rBigMap.get(curNode);
			//如果一个数左边和右边都没有比它大的数，那么这个数就是整个数组的最大值，即是MaxTree的头结点
			if(left == null && right == null) {
				head = curNode;
			}else if(left == null) {
				if(right.left == null) {
					right.left = curNode;
				}else {
					right.right = curNode;
				}
			}else if(right == null) {
				if(left.left == null) {
					left.left = curNode;
				}else {
					left.right = curNode;
				}
			}else {
				//每一个数的父节点是它左边第一个比它大的数和它右边第一个比它大的数中，比较小的那个
				Node parent = left.value < right.value ? left : right;
				if(parent.left == null) {
					parent.left = curNode;
				}else {
					parent.right = curNode;
				}
			}
		}
		return head;
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