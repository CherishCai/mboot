package cn.cherish.mboot.arithmetic.sort;

public class BubbleSort {

	/**
	 * 冒泡排序
	 * @param arr
	 * @return
	 */
	public static int[] sort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int min = arr[i];
			for (int j = i+1; j < arr.length; j++) {
				if (min > arr[j]) {
					arr[i] = arr[j];
					arr[j] = min;
					min = arr[i];
				}
			}
		}
		return arr;
	}
	/**
	 * 只为了测试
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] arr = new int[100000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*arr.length);
		}
		
		long start = System.currentTimeMillis();
		BubbleSort.sort(arr);
		
		long end = System.currentTimeMillis();
		System.out.println("希尔排序耗时："+(end - start));
		
		for (int k = 0; k < 100; k++) {
			System.out.print(arr[k] + " ");
		}
		System.out.println("时间复杂度：O(N^2)");
		
		int a = 88,b = 77;
		a = a^b;
		b = a^b;
		a = a^b;
		System.out.println("a="+a);
		System.out.println("b="+b);
	}

}
