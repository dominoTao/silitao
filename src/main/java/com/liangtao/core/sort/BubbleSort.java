package com.liangtao.core.sort;

/**
 * 
 * 冒泡排序
 * 
 * 时间复杂度：O(n^2)
 * 稳定性：稳定
 * @author tao
 *
 */
public class BubbleSort {
	/**
	 * 如：a = 2, b = 33
	 * a = a ^ b;   a = 35
	 * b = b ^ a;   b = 2
	 * a = a ^ b;   a = 33
	 */
	/**
	 * 此处用法疑问：在bubble_sort1()和bubble_sort2()中，if语句中的三行代码并不能直接带入此方法执行
	 */
	private static void swap(int a, int b) {
		a = a ^ b;
		b = b ^ a;
		a = a ^ b;
	}
	public void bubble_sort1(int a[], int n) {
		int i , j;
		for(i = n - 1; i > 0; i--) {
			//将a[0```i]中最大的数据放在末尾
			for(j = 0 ; j < i; j++) {
				if(a[j] > a[j+1]) {
					a[j] = a[j] ^ a[j+1];
					a[j+1] = a[j+1] ^ a[j];
					a[j] = a[j] ^ a[j+1];
				}
			}
		}
	}
	public void bubble_sort2(int a[], int n) {
		int i, j;
		int flag;
		
		for(i =n-1; i>0; i--) {
			flag = 0;
			for(j=0; j<i; j++) {
				if(a[j] >a[j+1]) {
					a[j] = a[j] ^ a[j+1];
					a[j+1] = a[j+1] ^ a[j];
					a[j] = a[j] ^ a[j+1];
					flag = 1;
				}
			}
			if(flag == 0) {
				break;
			}
		}
	}
	//打印完整序列
	private void printAll(int[] list){
		for(int value : list) {
			System.out.print(value + "\t");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		int a = 2;
		int b = 33;
		swap(a, b);
		System.out.println(a+"\t"+b);
		
		int[] array = {50,123,543,187,49,30,0,2,11,100};
		BubbleSort bubble = new BubbleSort();
		System.out.println("排序前：\t\t");
		bubble.printAll(array);
		
		bubble.bubble_sort1(array, array.length);
		System.out.println("排序后：\t\t");
		bubble.printAll(array);
		
		bubble.bubble_sort2(array, array.length);
		System.out.println("排序后：\t\t");
		bubble.printAll(array);
	}
}
