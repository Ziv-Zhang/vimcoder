package com.vimcoder.leetcode.string;

/**
 * https://leetcode-cn.com/problems/count-and-say/description/
 * 
 * 报数序列是指一个整数序列，按照其中的整数的顺序进行报数，得到下一个数
 * 
 * @author apple
 *
 */
public class CountAndSay {
	public String countAndSay(int n) {
		if (n == 1)
			return "1";
		String rest = "1";
		StringBuilder tmp = new StringBuilder();
		for (int i = 1; i < n; i++) {
			int count = 0;
			for (int j = 0; j < rest.length(); j++) {
				if(j==0) {
					count++;
					continue;
				}
				char now = rest.charAt(j);
				char pre = rest.charAt(j - 1);
				if (now == pre) {
					count++;
				} else {
					tmp.append(count).append(pre);
					count = 1;
				}
			}
			tmp.append(count).append(rest.charAt(rest.length() - 1));
			rest = tmp.toString();
			tmp.delete(0, tmp.length());
		}

		return rest;
	}

	public static void main(String[] args) {
		System.out.println(new CountAndSay().countAndSay(6));
	}
}
