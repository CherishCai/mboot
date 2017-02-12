package cn.cherish.mboot.arithmetic.sort;

public class SelectSort {

	/**
	 * 选择排序
	 * @param arr
	 * @return
	 */
	public static int[] sort(int[] arr){
		int index,min;
		for (int i = 0; i < arr.length; i++) {
			index = i;
			min = arr[i];
			for (int j = i+1; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					index = j;
				}
			}
			arr[index] = arr[i];
			arr[i] = min;
			//用位操作交换数据
//			arr[i] = arr[i]^arr[index];
//			arr[index] = arr[i]^arr[index];
//			arr[i] = arr[i]^arr[index];
		}
		return arr;
	}
	
	
	public static void main(String[] args) {
		int[] arr = new int[100000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*arr.length);
		}
		
		long start = System.currentTimeMillis();
		SelectSort.sort(arr);
		
		long end = System.currentTimeMillis();
		System.out.println("希尔排序耗时："+(end - start));
		
		for (int k = 0; k < 100; k++) {
			System.out.print(arr[k] + " ");
		}
		System.out.println("时间复杂度：O(N^2),因为交换次数少比冒泡排序稍微好一点");
		
	}

}
