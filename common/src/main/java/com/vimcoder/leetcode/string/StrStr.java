package com.vimcoder.leetcode.string;

/**
 * https://leetcode-cn.com/problems/implement-strstr/description/
 * 
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中 找出 needle 字符串出现的第一个位置
 * (从0开始)。如果不存在，则返回 -1
 * 
 * @author apple
 *
 */
public class StrStr {
	public int strStr(String haystack, String needle) {
		return haystack.indexOf(needle);
	}
}
