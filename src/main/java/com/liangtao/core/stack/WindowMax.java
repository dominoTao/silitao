package com.liangtao.core.stack;

import java.util.LinkedList;

/**
 * 
 * 生成窗口最大值数组
 * @author Administrator
 *
 */
public class WindowMax {
	/**
	 * @param arr 原始数组
	 * @param w 窗口长度
	 * @return 窗口最大值数组
	 */
	public int[] getMaxWindow(int[] arr, int w) {
		if(arr == null || w < 1 || arr.length < w)
			return null;
		//定义一个双端队列，用于从大到小依次存放arr的下标
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		//窗口最大值数组
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		for(int i = 0; i < arr.length; i++){
			while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
				qmax.pollLast();
			}
			qmax.addLast(i);
			//i - w 表示 qmax中要去除的对头元素
			if(qmax.peekFirst() == i - w) {
				qmax.pollFirst();
			}
			//开始组装窗口最大数组
			if(i >= w - 1) {
				res[index++] = arr[qmax.peekFirst()];
			}
		}
		return res;
	}
	public static void main(String[] args) {
		WindowMax wm = new WindowMax();
		int[] arr = {4,3,5,4,3,3,6,7};
		int[] out = wm.getMaxWindow(arr, 3);
		for(int i : out) {
			System.out.println(i);
		}
	}
}
