package com.vimcoder.leetcode.array;

/**
 * 
 * https://leetcode-cn.com/problems/plus-one/description/
 * 
 * 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * 
 * @author zhang
 *
 */
public class PlusOne {
	public int[] plusOne(int[] digits) {
		int tmp = 1;
		for (int i = digits.length - 1; i >= 0 && tmp > 0; i--) {
			int sum = digits[i] + tmp;
			digits[i] = sum % 10;
			tmp = sum / 10;
		}

		if (tmp == 0)
			return digits;

		int[] rest = new int[digits.length + 1];
		rest[0] = 1;
		for (int i = 1; i < rest.length; i++) {
			rest[i] = digits[i - 1];
		}
		return rest;
	}

	public int[] plusOne2(int[] digits) {
		for (int i = digits.length - 1;;) {
			int sum = digits[i] + 1;
			digits[i] = sum == 10 ? 0 : sum;
			i--;
			if (sum < 10) {
				return digits;
			}
			if (i < 0) {
				break;
			}
		}

		int[] rest = new int[digits.length + 1];
		rest[0] = 1;
		for (int i = 1; i < rest.length; i++) {
			rest[i] = digits[i - 1];
		}
		return rest;
	}

	public static void main(String[] args) {
		new PlusOne().plusOne2(new int[] { 1, 2, 3 });
	}
}
