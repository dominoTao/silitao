package com.liangtao.core.stack;

import java.util.Stack;

/**
 * 非递归方法：用栈类模拟整个过程
 * 
 * 分析：修改后的汉诺塔不能让任何塔从左直接移动到右 ，也不能从右直接移动到左，而是要经过中间。
 * 也就是说，实际动作只有四个：
 * 		左	->	中
 * 		中	->	右
 * 		右	->	中
 * 		中	->	左
 * 现在把左中右三个柱子抽象成三个栈，依次记为LS,MS,RS.
 * 最初所有的塔都在LS上。
 * 那么如上4个动作就可以看成是：某一个栈（from）把栈顶元素弹出，然后压入到另一个栈里（to），作为这一个栈（to）的栈顶
 * 
 * 游戏的第一个动作一定是L->M,这是显然的
 * 在走出最少步数的过程中的任何时刻，四个动作中只有一个动作不违反小压大和相邻不可逆原则，另外三个动作一定会违反。
 * 
 * 相邻不可逆原则：L->M和M->L过程互为逆过程，任何两个相邻的动作都不是互为逆过程
 * @author tao
 */
public class HanoiProblemDemo2 {
	public enum Action{
		No, LToM, MToL, MToR, RToM
	}
	
	public static void main(String[] args) {
		HanoiProblemDemo2 h = new HanoiProblemDemo2();
		h.hanoiProblem2(3, "<左<", "|中|", ">右>");
	}
	
	public synchronized int hanoiProblem2(int num, String left, String mid, String right){
		Stack<Integer> lS = new Stack<Integer>();
		Stack<Integer> mS = new Stack<Integer>();
		Stack<Integer> rS = new Stack<Integer>();
		lS.push(Integer.MAX_VALUE);
		mS.push(Integer.MAX_VALUE);
		rS.push(Integer.MAX_VALUE);
		for(int i = num; i > 0; i--) {
			lS.push(i);
		}
		Action[] record = {Action.No};
		int step = 0;
		while(rS.size() != num + 1) {
			step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
			step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
			step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
			step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
		}
		return step;
	}
	public synchronized static int fStackTotStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack, String from, String to) {
		if(record[0] != preNoAct && fStack.peek() < tStack.peek()) {
			tStack.push(fStack.pop());
			System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
			record[0] = nowAct;
			return 1;
		}
		return 0;
	}
}
