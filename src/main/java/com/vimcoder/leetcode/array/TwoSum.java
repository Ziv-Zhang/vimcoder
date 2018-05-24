package com.vimcoder.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/description/
 * 
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 * 
 * @author zhang
 *
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++ ) {
        	int tmp = target - nums[i];
        	Integer index = map.get(tmp);
        	if(index == null) {
        		map.put(nums[i], i);
        	}else {
        		return new int[] {index, i};
        	}
        }
        return null;
    }
}
