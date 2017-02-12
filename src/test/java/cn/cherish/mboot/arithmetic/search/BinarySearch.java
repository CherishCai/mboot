package cn.cherish.mboot.arithmetic.search;


import cn.cherish.mboot.arithmetic.sort.QuickSort;

public class BinarySearch {

	/**
	 * 对有序的数据进行二分查询
	 * @param arr
	 * @param val
	 * @return
	 */
	public static boolean search(int[] arr, int val){
		
		int index = findIndex(arr, val);
		if (index == -1) {
			return false;
		}
		return true;
	}
	/**
	 * 找到对应val的下标
	 * @param arr
	 * @param val
	 * @return
	 */
	public static int findIndex(int[] arr, int val) {
		int left = 0,right = arr.length - 1;
		int pivot = -1;
		while (true) {
			pivot = (left + right)>>1;
			if (arr[pivot] == val) {
				break;
			}
			if (left >= right) {
				pivot = -1;
				break;
			}
			if (arr[pivot] < val) {
				left = pivot + 1;
			}else {
				right = pivot - 1;
			}
		}
		return pivot; 
	}

	/**
	 * 打印显示
	 * @param arr
	 */
	public static void display(int[] arr){
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	public static void main(String[] args) {

		int[] arr = new int[10000000];
		int value = 4;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i*4;
		}
//		display(arr);
		long start = System.currentTimeMillis();
		boolean exist = search(arr, value);
		long end = System.currentTimeMillis();
		System.out.println("耗时："+(end-start));
		System.out.println("是否存在"+value+"？：" + exist);
		
		
		QuickSort.main(args);
	}

}
