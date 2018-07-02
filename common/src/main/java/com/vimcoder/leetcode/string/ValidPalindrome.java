package com.vimcoder.leetcode.string;

/**
 * https://leetcode-cn.com/problems/valid-palindrome/description/
 * 
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 
 * @author apple
 *
 */
public class ValidPalindrome {
	public boolean isPalindrome(String s) {
		int l = 0, r = s.length() - 1;
		while (l < r) {
			int lc, rc;
			while (l < r) {
				lc = s.charAt(l);
				// [0-9]48~57,[A-Z]65~90,[a-z]97~122
				if (lc > 47 && lc < 58 || lc > 64 && lc < 91 || lc > 96 && lc < 123) {
					break;
				}
				l++;
			}
			while (l < r) {
				rc = s.charAt(r);
				if (rc > 47 && rc < 58 || rc > 64 && rc < 91 || rc > 96 && rc < 123) {
					break;
				}
				r--;
			}
			lc = s.charAt(l);
			rc = s.charAt(r);
			if (lc > 96) {
				lc -= 32;
			}
			if (rc > 96) {
				rc -= 32;
			}
			if (lc != rc) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(new ValidPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
	}
}
