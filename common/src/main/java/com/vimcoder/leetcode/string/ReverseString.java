package com.vimcoder.leetcode.string;

/**
 * https://leetcode-cn.com/problems/reverse-string/description/
 * 
 * 请编写一个函数，其功能是将输入的字符串反转过来。
 * 
 * @author zhang
 *
 */
public class ReverseString {
    public String reverseString(String s) {
        char[] b = s.toCharArray();
        char tmp;
        for(int i=0; i<b.length/2; i++) {
        	tmp = b[i];
        	b[i] = b[b.length - i -1];
        	b[b.length - i -1] = tmp;
        }
        return new String(b);
    }
    
    public static void main(String[] args) {
		System.out.println(new ReverseString().reverseString("as1"));
	}
}
