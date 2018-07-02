package com.vimcoder.leetcode.array;

/**
 * 
 * https://leetcode-cn.com/problems/rotate-array/description/
 * 
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 
 * solution：https://leetcode.com/problems/rotate-array/solution/
 * 
 * @author zhang
 *
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        for(int i=1; i<=k; i++){
            int previous = nums[nums.length -1];
            for(int j=0; j<nums.length; j++){
            	int tmp = nums[j];
                nums[j] = previous;
                previous = tmp;
            }
        }
    }
    
    //超出时间限制，作废
	public void rotate2(int[] nums, int k) {
		int previous = nums[nums.length - 1];
		for (int i = k - 1;;) {
			int tmp = nums[i];
			nums[i] = previous;
			previous = tmp;
			i += k;
			if (i >= nums.length) {
				i -= nums.length;
			}
			if (i == k) {
				for (; i < nums.length; i += k) {
					tmp = nums[i];
					nums[i] = previous;
					previous = tmp;
				}
				break;
			}
		}
	}
    
    public static void main(String[] args) {
		int[] array = new int[] {1,2,3,4,5,6,7};
		new RotateArray().rotate2(array, 3);
	}
}
