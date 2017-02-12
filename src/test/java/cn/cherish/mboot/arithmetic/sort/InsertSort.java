package cn.cherish.mboot.arithmetic.sort;

public class InsertSort {

	/**
	 * 插入排序，嵌套了二分查找
	 * @param arr->整型数组
	 * @return int[]->整型数组
	 */
	public static int[] sort(int[] arr){
		arr = sort(arr, 0, arr.length);
		return arr;
	}
	/**
	 * 插入排序，嵌套了二分查找
	 * @param arr->整型数组
	 * @param start->开始位置
	 * @param end->结束位置
	 * @return int[]->整型数组
	 */
	public static int[] sort(int[] arr, int start, int end){
		int temp = 0;
		int index = 0;
		for (int i = start + 1; i < end; i++) {
			temp = arr[i];

			//二分查找插入点
			int left = start,right = i - 1;
			index = -1;
			while (true) {
				index = (left + right)>>1;
				if (arr[index] == temp) {
					index++;
					break;
				}
				if (arr[index] < temp) {
					left = index + 1;
				}else {
					right = index - 1;
				}
				if (left >= right) {
					index = left;
					if (arr[left] < temp) {
						index = left + 1;
					}
					break;
				}
			}
			if (index != -1) {
				insert(arr,index,i,temp);
			}
		}
		return arr;
	}
	/**
	 * 执行插入
	 * @param arr
	 * @param begin
	 * @param end
	 */
	protected static void insert(int[] arr,int begin,int end,int temp) {
		for (int i = end; i > begin; i--) {
			arr[i] = arr[i - 1];
		}
		arr[begin] = temp;
	}
	
	public static void main(String[] args) {
		System.out.println("插入排序时间复杂度：O(N^2),处理基本有序的数据有利，我还嵌套了二分查找");
		int[] arr = new int[100000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*arr.length);
//			arr[i] = i*2;
		}
		long start = System.currentTimeMillis();
		InsertSort.sort(arr);
		long end = System.currentTimeMillis();
		System.out.println("插入排序耗时："+(end - start));
		
		for (int i = arr.length>>6; i < arr.length>>5; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		
		//选择
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*arr.length);
		}
		long start2 = System.currentTimeMillis();
		SelectSort.sort(arr);
		long end2 = System.currentTimeMillis();
		System.out.println("选择排序耗时："+(end2 - start2));
		for (int i = arr.length>>6; i < arr.length>>5; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		
	}

}
