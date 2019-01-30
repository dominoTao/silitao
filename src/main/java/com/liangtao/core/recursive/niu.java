package com.liangtao.core.recursive;

/**
 * 母牛生母牛算法
 * @author tao
 */
public class niu {
	public int c3(int n) {
		if (n < 1) {
			return 0;
		}
		if (n == 1 || n == 2 || n == 3) {
			return n;
		}
		int base[][] = { { 1, 1, 0 }, { 0, 0, 1 }, { 1, 0, 0 } }; // 构造矩阵
		// int [][] res = matrixPower(base,n-3); //矩阵n-3次方

		// return 3*res[0][0]+2*res[1][0]+res[2][0];
		return 1;
	}
}
