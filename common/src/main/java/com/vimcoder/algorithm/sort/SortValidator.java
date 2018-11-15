package com.vimcoder.algorithm.sort;

import java.util.Arrays;

public class SortValidator {
	
	public static int[] randomArray(int maxVal, int maxSize) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxVal + 1)) - (int) (Math.random() * (maxVal + 1));
		}
		return arr;
	}
	
	public static int[] copyArray(int[] arr) {
		return Arrays.copyOf(arr, arr.length);
	}

	public static void trueSort(int[] a) {
		Arrays.sort(a);
	}
	
	public static boolean isValid(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}
}
