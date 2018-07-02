package com.vimcoder.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/contains-duplicate/description/
 * 
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 * 
 * @author zhang
 *
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
    	Object obj = new Object();
        Map<Integer, Object> exists = new HashMap<Integer, Object>();
        for(int i : nums){
            if(exists.containsKey(i)) {
            	return true;
            }
            exists.put(i, obj);
        }
        return false;
    }
}
