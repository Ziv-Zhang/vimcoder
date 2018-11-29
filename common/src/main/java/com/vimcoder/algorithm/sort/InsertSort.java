package com.vimcoder.algorithm.sort;

import org.junit.Test;

public class InsertSort implements Sortable {

	@Override
	public void sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int j = i;
			int tmp = arr[i];
			for (; j > 0 && arr[j - 1] > tmp; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = tmp;
		}
	}

	@Test
	public void test() {
		System.out.println(valid());
	}

}
