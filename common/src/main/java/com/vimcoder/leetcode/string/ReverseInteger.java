package com.vimcoder.leetcode.string;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 * 
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 * 
 * @author zhang
 *
 */
public class ReverseInteger {
	//重写
	public int reverse(int x) {
		String a = String.valueOf(x);
		char[] arr = a.toCharArray();
		char tmp;
		int e = x > 0 ? 0 : 1;
		for (int i = 0; i < (arr.length - e) / 2; i++) {
			tmp = arr[i + e];
			arr[i + e] = arr[arr.length - 1 - i];
			arr[arr.length - 1 - i] = tmp;
		}
		long l = Long.parseLong(new String(arr));
		if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE) {
			return 0;
		}
		return (int) l;
	}

	public static void main(String[] args) {
		System.out.println(new ReverseInteger().reverse(-51));
		System.out.println(Integer.MAX_VALUE);

	}
}
