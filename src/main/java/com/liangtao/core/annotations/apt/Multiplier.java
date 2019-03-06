package com.liangtao.core.annotations.apt;
/**
 * 测试类，用于提取公共方法来作为接口的元素
 */
@ExtractInterface("IMultiplier")
public class Multiplier {
	public int multiply(int x, int y) {
		int total = 0;
		for(int i = 0; i < x; i++) {
			total = add(total, y);
		}
		return total;
	}
	private int add(int x, int y) {
		return x + y;
	}
	public static void main(String[] args) {
		Multiplier m = new Multiplier();
		System.out.println("11 * 16 = " + m.multiply(11, 16));
	}
}
