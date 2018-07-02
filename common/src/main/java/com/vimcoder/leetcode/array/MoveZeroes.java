package com.vimcoder.leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/move-zeroes/description/
 * 
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 
 * @author zhang
 *
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
    	// right 和 left中间即为0的区间, left为第一个0所在都索引
    	int left = 0, right = 0;
    	while(right<nums.length) {
    		//如果right不为0，则交换，索引都后移一位
    		//如果right为0，则只有right向后移
    		if(nums[right] != 0) {
    			int tmp = nums[left];
    			nums[left] = nums[right];
    			nums[right] = tmp;
    			left++;
    		}
    		right++;
    	}
    }
    
    public static void main(String[] args) {
		new MoveZeroes().moveZeroes(new int[] {0,2,0,4});
	}
}
