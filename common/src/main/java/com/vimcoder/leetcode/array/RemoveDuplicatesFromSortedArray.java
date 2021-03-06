package com.vimcoder.leetcode.array;

/**
 * 
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/description/
 * 
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 
 * @author zhang
 *
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int index = 0;
        // 如果不一样，就移动元素，覆盖掉重复都元素
        for(int i=1; i<nums.length; i++){
            if(nums[index] != nums[i]){
                nums[++index] = nums[i];
            }
        }
        
        return index+1;
    }
}
