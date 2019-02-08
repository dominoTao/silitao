package com.liangtao.core.stack;

/**
 * 变种汉诺塔问题：在经典汉诺塔问题基础上，添加一条规则：不能从最左边直接移动到最右边或者从最右边直接移动到最左边，必须经过中间的柱子
 * 
 * 方法一:递归
 * 方法二:非递归，用栈来模拟汉诺塔的三个塔
 * @author tao
 *
 */
public class HanoiProblemDemo1 {
	public static void main(String[] args) {
		HanoiProblemDemo1 h = new HanoiProblemDemo1();
		h.hanoiProblem1(3, "<左<", "|中|", ">右>");
	}
	 public synchronized int hanoiProblem1(int num, String left, String mid, String right) {
		 if(num < 1) {
			 return 0;
		 }
		 return process(num, left, mid, right, left, right);
	 }
	 public synchronized int process(int num, String left, String mid, String right, String from, String to) {
		 if(num == 1) {
			 if(from.equals(mid) || to.equals(mid)) {
				 System.out.println("Move 1 from " + from + " to " + to);
				 return 1;
			 }else {
				 System.out.println("Move 1 from " + from + " to " + mid);
				 System.out.println("Move 1 from " + mid + " to " + to);
				 return 2;
			 }
		 }
		 if(from.equals(mid) || to.equals(mid)) {
			 String another = (from.equals(left) || to.equals(left) ? right : left);
			 int part1 = process(num - 1, left, mid, right, from, another);
			 int part2 = 1;
			 System.out.println("Move " + num + " from " + from + " to " + to);
			 int part3 = process(num - 1, left, mid, right, another, to);
			 return part1 + part2 + part3;
		 }else {
			 int part1 = process(num -1, left, mid, right, from, to);
			 int part2 = 1;
			 System.out.println("Move " + num + " from " + from + " to " + mid);
			 int part3 = process(num - 1, left, mid, right, to, from);
			 int part4 = 1;
			 System.out.println("Move " + num + " from " + mid + " to " + to);
			 int part5 = process(num -1, left, mid, right, from, to);
			 return part1 + part2 + part3 + part4 + part5;
		 }
	 }
}
