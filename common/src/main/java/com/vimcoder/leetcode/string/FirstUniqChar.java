package com.vimcoder.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/description/
 * 
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 
 * @author zhang
 *
 */
public class FirstUniqChar {
	
	public int firstUniqChar2(String s) {
		int l, r;
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			l = s.indexOf(chars[i]);
			r = s.lastIndexOf(chars[i]);
			if (l == r && l != -1) {
				return l;
			}
		}

		return -1;
	}
	
	public int firstUniqChar(String s) {
		char[] chars = s.toCharArray();
		Map<Character, Integer> exists = new HashMap<>();
		for (char c : chars) {
			exists.put(c, exists.containsKey(c) ? 2 : 1);
		}
		for (int i = 0; i < chars.length; i++) {
			Integer count = exists.get(chars[i]);
			if (count == null || count == 1) {
				return i;
			}
		}
		return -1;
	}
}
