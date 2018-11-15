package com.vimcoder.algorithm.sort;

import org.junit.Test;

public class SelectSort implements Sortable {

	@Override
	public void sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			swap(arr, i, min);
		}
	}

	@Test
	public void test() {
		System.out.println(valid());
	}
}
