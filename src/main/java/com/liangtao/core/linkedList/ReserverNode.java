package com.liangtao.core.linkedList;

/**
 * 反转单向链表和双向链表
 * @author Administrator
 *
 */
public class ReserverNode {
	class Node{
		public int value;
		public Node next;
		public Node(int data) {
			this.value = data;
		}
	}
	class DoubleNode {
		public int value;
		public DoubleNode last;//上一个
		public DoubleNode next;//下一个
		public DoubleNode(int data) {
			this.value = data;
		}
	}
	/**
	 * 反转单向链表
	 * @param head
	 * @return 反转之后链表新的头节点
	 */
	public Node reverseList(Node head) {
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
	/**
	 * 反转双向链表
	 * @param head
	 * @return 反转之后链表新的头节点
	 */
	public DoubleNode reverseList2(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	}
	/**
	 * 反转部分单向链表
	 * @param head
	 * @param from
	 * @param to
	 * @return
	 */
	public Node reversePart(Node head, int from, int to) {
		int len = 0;
		Node node1 = head;
		Node fPre = null;
		Node tPos = null;
		while (node1 != null) {
			len ++;
			fPre = len == from - 1 ? node1 : fPre;
			tPos = len == to + 1 ? node1 : tPos;
			node1 = node1.next;
		}
		if(from > to || from < 1 || to > len) {
			return head;
		}
		node1 = fPre == null ? head : fPre.next;
		Node node2 = node1.next;
		node1.next = tPos;
		Node next = null;
		while(node2 != tPos) {
			next = node2.next;
			node2.next = node1;
			node1 = node2;
			node2 = next;
		}
		if(fPre != null) {
			fPre.next = node1;
			return head;
		}
		return node1;
	}
}
