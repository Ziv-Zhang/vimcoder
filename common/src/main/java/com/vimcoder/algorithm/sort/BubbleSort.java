package com.vimcoder.algorithm.sort;

import org.junit.Test;

/**
 * 冒泡排序
 * 
 * @author apple
 */
public class BubbleSort implements Sortable {

	@Override
	public void sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1])
					swap(arr, j, j + 1);
			}
		}
	}

	@Test
	public void test() {
		System.out.println(valid());
	}
}
