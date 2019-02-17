package com.liangtao.core.linkedList;

/**
 * 删除单链表和双链表中倒数第K个节点
 * 实现两个函数，一个可以删除单链表中倒数第K个节点，另一个可以删除双链表中倒数第K个节点
 * 
 * 链表长度为N，时间复杂度达到O(N)，额外空间复杂度达到O(1)
 * @author Administrator
 *
 */
public class DeleteLastKthNode {
	class Node {
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
	 * 单链表删除倒数第K个节点
	 * @param head
	 * @param lastKth
	 * @return 
	 */
	public Node removeLastKthNode(Node head, int lastKth) {
		if(head == null || lastKth < 1) {
			return head;
		}
		Node cur = head;
		while(cur != null) {
			lastKth--;
			cur = cur.next;
		}
		if(lastKth == 0) {
			head = head.next;
		}
		if(lastKth < 0) {
			cur = head;
			while(++lastKth != 0) {
				cur = cur.next;
			}
			cur.next = cur.next.next;
		}
		return head;
	}
	/**
	 * 双链表删除倒数第K个节点
	 * @param head
	 * @param lastKth
	 * @return
	 */
	public DoubleNode removeLastKthNode(DoubleNode head, int lastKth) {
		if(head == null || lastKth < 1) {
			return head;
		}
		DoubleNode cur = head;
		while(cur != null) {
			lastKth--;
			cur = cur.next;
		}
		if(lastKth == 0) {
			head = head.next;
			head.last = null;
		}
		if(lastKth < 0) {
			cur = head;
			while(++lastKth != 0) {
				cur = cur.next;
			}
			DoubleNode newNext = cur.next.next;
			cur.next = newNext;
			if(newNext != null) {
				newNext.last = cur;
			}
		}
		return head;
	}
}

