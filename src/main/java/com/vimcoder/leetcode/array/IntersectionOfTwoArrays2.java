package com.vimcoder.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/description/
 * 
 * 给定两个数组，写一个方法来计算它们的交集。
 * 
 * @author zhang
 *
 */
public class IntersectionOfTwoArrays2 {
	public int[] intersect(int[] nums1, int[] nums2) {
		List<Integer> result = new ArrayList<>();
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		for (int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
			if (nums1[i] == nums2[j]) {
				result.add(nums1[i]);
				i++;
				j++;
				continue;
			}
			if (nums1[i] < nums2[j]) {
				i++;
			} else {
				j++;
			}
		}
		int[] arr = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			arr[i] = result.get(i);
		}
		return arr;
	}

	public static void main(String[] args) {

	}
}
