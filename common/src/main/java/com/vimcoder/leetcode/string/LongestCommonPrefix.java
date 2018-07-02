package com.vimcoder.leetcode.string;

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0)
			return "";
		if(strs.length == 1)
			return strs[0];
		int min = strs[0].length();
		for(String str : strs){
			if(str.length() < min)
				min = str.length();
		}

		for(int i = 0; i < min; i++){

			char c = strs[0].charAt(i);
			for(int j = 1; j < strs.length; j++){
				if(c != strs[j].charAt(i)){
					return strs[0].substring(0, i);
				}
			}
		}

		return strs[0].substring(0, min);
	}
}
