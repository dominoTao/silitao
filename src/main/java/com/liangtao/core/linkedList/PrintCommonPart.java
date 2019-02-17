package com.liangtao.core.linkedList;

import java.util.LinkedList;

/**
 * 打印两个链表的公共部分
 * 
 * 给定两个链表的头指针Head1和Head2，打印两个链表的公共部分
 * @author Administrator
 *
 */
public class PrintCommonPart {
	public static void main(String[] args) {
		LinkedList<Node> link1 = new LinkedList<Node>();
		LinkedList<Node> link2 = new LinkedList<Node>();
		link1.add(new Node(1));
		link1.add(new Node(2));
		link1.add(new Node(3));
		link1.add(new Node(4));
		link1.add(new Node(5));
		link1.add(new Node(6));
		link2.add(new Node(3));
		link2.add(new Node(4));
		link2.add(new Node(5));
		link2.add(new Node(6));
		PrintCommonPart pcp = new PrintCommonPart();
		pcp.printCommonPart(link1.getFirst(), link2.getFirst());
	}
	public void printCommonPart(Node head1, Node head2) {
		System.out.println("Common Part: ");
		while(head1 != null && head2 != null) {
			if(head1.value < head2.value) {
				head1 = head1.next;
			}else if(head1.value > head2.value) {
				head2 = head2.next;
			}else{
				System.out.println(head1.value+" ");
				head1 = head1.next;
				head2 = head2.next;
			}
		}
		System.out.println();
	}
}
class Node{
	public int value;
	public Node next;
	public Node(int data) {
		this.value = data;
	}
}