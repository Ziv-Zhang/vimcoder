package com.vimcoder.leetcode.string;

/**
 * https://leetcode-cn.com/problems/string-to-integer-atoi/description/
 * 
 * 实现 atoi，将字符串转为整数。
 * 
 * @author apple
 *
 */
public class StringToIntegerAtoi {
	public int myAtoi(String str) {
		int l = 0;
		Character sign = null;
		boolean s = false;
		while (l < str.length()) {
			int c = str.charAt(l);
			if (c == ' ') {
				if (!s) {
					l++;
					continue;
				}
				return 0;
			}
			if (c == '0') {
				l++;
				s = true;
				continue;
			}
			if (c == '-') {
				if (!s) {
					sign = '-';
					l++;
					s = true;
					continue;
				}
				return 0;
			}
			if (c == '+') {
				if (!s) {
					sign = '+';
					l++;
					s = true;
					continue;
				}
				return 0;
			}
			if (c > '0' && c <= '9') {
				break;
			}
			return 0;
		}
		if (l >= str.length()) {
			return 0;
		}

		int r = l + 1;
		while (r < str.length() && r < l + 12) {
			int c = str.charAt(r);
			if (c >= '0' && c <= '9') {
				r++;
				continue;
			}
			break;
		}
		String subStr = str.substring(l, r);
		if (sign != null) {
			subStr = sign + subStr;
		}
		long res = Long.parseLong(subStr);
		if (res > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		if (res < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		return (int) res;
	}

	public static void main(String[] args) {
		System.out.println(new StringToIntegerAtoi().myAtoi("0  123"));
	}
}
