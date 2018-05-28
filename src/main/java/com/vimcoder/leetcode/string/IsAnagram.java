package com.vimcoder.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class IsAnagram {
	
	public boolean isAnagram2(String s, String t) {
		int[] ints = new int[26];
		for (int i = 0; i < s.length(); i++) ints[s.charAt(i) - 'a']++;
		for (int i = 0; i < t.length(); i++) ints[t.charAt(i) - 'a']--;
		for (int i : ints) if (i != 0) return false;
		return true;
	}
	
	public boolean isAnagram(String s, String t) {

		Map<Character, Integer> tMap = new HashMap<>();

		for (char c : t.toCharArray()) {
			Integer count = tMap.get(c);
			tMap.put(c, count == null ? 1 : ++count);
		}
		for (char c : s.toCharArray()) {
			Integer count = tMap.get(c);
			if (count == null || count == 0) {
				return false;
			}
			count--;
			if (count == 0)
				tMap.remove(c);
			else
				tMap.put(c, count);
		}
		return tMap.size()==0;
	}
	
	public static void main(String[] args) {
		System.out.println(new IsAnagram().isAnagram2("ab", "a"));
	}
}
