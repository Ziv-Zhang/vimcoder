package com.vimcoder.algorithm.sort;

import java.util.Arrays;

public interface Sortable {
	void sort(int[] arr);

	default boolean valid() {
		for (int i = 0; i < 100000; i++) {
			int[] arr1 = SortValidator.randomArray(1000, 20);
			int[] arr2 = SortValidator.copyArray(arr1);

			sort(arr1);
			SortValidator.trueSort(arr2);
			if (!SortValidator.isValid(arr1, arr2)) {
				System.out.println("============error=============");
				System.out.println("your sort:" + Arrays.toString(arr1));
				System.out.println("true sort:" + Arrays.toString(arr2));
				System.out.println("===========loop[" + i + "]============");
				return false;
			}
		}
		return true;
	}

	default void swap(int[] arr, int a, int b) {
		if (a == b)
			return;
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
