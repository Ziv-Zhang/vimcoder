package com.vimcoder.leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/rotate-image/description/
 * 
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 
 * @author zhang
 *
 */
public class RotateImage {
	public void rotate(int[][] matrix) {
		int n = matrix.length - 1;
		for (int i = 0; i < matrix.length / 2; i++) {
			for (int j = i; j < n - i; j++) {
				int x = i, y = j;
				int previous = matrix[x][y], tmp;
				for (int k = 0; k < 4; k++) {
					tmp = matrix[y][n - x];
					x = x + y;
					y = x - y;
					x = x - y;
					y = n - y;
					matrix[x][y] = previous;
					previous = tmp;
				}

			}
		}
	}
    
    public static void main(String[] args) {
//    	int[][] a = new int[3][];
//    	a[0] = new int[] {1,2,3};
//    	a[1] = new int[] {4,5,6};
//    	a[2] = new int[] {7,8,9};
    	int[][] a = new int[4][];
    	a[0] = new int[] {5,1,9,11};
    	a[1] = new int[] {2,4,8,10};
    	a[2] = new int[] {13,3,6,7};
    	a[3] = new int[] {15,14,12,16};
		new RotateImage().rotate(a);
	}
}
