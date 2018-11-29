package com.vimcoder.algorithm.sort;

public class SortTest implements Sortable {

	// 冒泡排序
	private void maopao(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			// 循环遍历length-1遍，最后一个元素不需要遍历
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
	}

	// 选择排序
	private void selector(int[] arr) {
		// 选取最小的放在i位置
		for (int i = 0; i < arr.length - 1; i++) {
			int min = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			swap(arr, i, min);
		}
	}

	// 插入排序
	private void insert(int[] arr) {
		// 从后往前
		for (int i = 1; i < arr.length; i++) {
			int v = arr[i];
			int j = i;
			for (; j > 0 && v < arr[j - 1]; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = v;
		}
	}

	// 希尔排序
	private void xier(int[] arr) {
		for (int seq = arr.length / 2; seq >= 1; seq /= 2) {
			for (int i = seq; i < arr.length; i++) {
				int tmp = arr[i];
				int j = i;// 用来标记最后一个移动的元素
				for (; j - seq >= 0 && tmp < arr[j - seq]; j -= seq) {
					arr[j] = arr[j - seq];
				}
				if (arr[j] != tmp)
					arr[j] = tmp;
			}
		}
	}

	// 归并排序
	private void guiBing(int[] arr) {
		guiBingSplit(arr, 0, arr.length - 1, new int[arr.length]);
	}

	private void guiBingSplit(int[] arr, int left, int right, int[] tmp) {
		if (left < right) {
			int mid = (left + right) / 2;
			guiBingSplit(arr, left, mid, tmp);
			guiBingSplit(arr, mid + 1, right, tmp);
			guiBingMerge(arr, left, mid, right, tmp);
		}
	}

	private void guiBingMerge(int[] arr, int left, int mid, int right, int[] tmp) {
		int k = 0, i = left, j = mid + 1;
		while (i <= mid && j <= right) {
			// 判断大小，复制到tmp中
			if (arr[i] < arr[j]) {
				tmp[k++] = arr[i++];
			} else {
				tmp[k++] = arr[j++];
			}
		}
		while (i <= mid) {
			tmp[k++] = arr[i++];
		}
		while (j <= right) {
			tmp[k++] = arr[j++];
		}

		// 将所有在tmp中已经排好序的数据复制回原数组中
		k = 0;
		while (left <= right) {
			arr[left++] = tmp[k++];
		}
	}

	// 堆排序
	private void heap(int[] arr) {
		// 如果要从小到大进行排序，首先要构建一个大顶堆
		// 父节点：arr.length/2-1
		// 左子节点：父节点*2+1
		for (int p = arr.length / 2 - 1; p >= 0; p--) {
			heapSubSort(arr, p, arr.length);
		}
		// 将构建的大顶堆，顶部元素取出放在队列末尾，继续循环
		for (int i = arr.length - 1; i > 0; i--) {
			swap(arr, 0, i);
			heapSubSort(arr, 0, i);
		}
	}

	// 目的是构建一个大顶堆
	private void heapSubSort(int[] arr, int parent, int length) {
		int tmp = arr[parent];
		int child = parent * 2 + 1;
		while (child < length) {
			// 首先判断左右子节点的大小，选择最大的那个
			if (child + 1 < length && arr[child] < arr[child + 1]) {
				child++;
			}
			// 判断子节点中最大的那个与父节点比较大小，如果父节点更大，就直接退出
			if (arr[child] < tmp) {
				break;
			}
			// 顶点与最大子节点进行交换
			arr[parent] = arr[child];
			parent = child;
			child = child * 2 + 1;
		}
		arr[parent] = tmp;
	}

	@Override
	public void sort(int[] arr) {
		// maopao(arr);
		// selector(arr);
		// insert(arr);
		// xier(arr);
		// guiBing(arr);
		heap(arr);
	}

	public static void main(String[] args) {
		System.out.println(new SortTest().valid());
	}
}
