package cn.cherish.mboot.arithmetic.sort;

/**
 * 蔡梦缘之快速排序
 * @author caimengyuan
 *
 */
public class QuickSort {

	/**
	 * 快速排序
	 * @param arr
	 * @return
	 */
	public static int[] sort(int[] arr){
		arr = sort(arr,0,arr.length-1);
		return arr;
	}
	/**
	 * 快速排序
	 * @param arr
	 * @param start
	 * @param end
	 * @return
	 */
	public static int[] sort(int[] arr, int start, int end) {
		quickSort(arr,start,end);
		return arr;
	}

	private static void quickSort(int[] arr, int start, int end) {
		if (start >= end - 2) {
			if (start >= end) {
				return;
			}else if(start == end - 1){
				//对两个数据项的排序
				if (arr[start] > arr[end]) {
					int temp = arr[end];
					arr[end] = arr[start];
					arr[start] = temp;
				}
				return;
			}else if(start == end - 2){
				//对三个数据项的排序
				int middle = (start + end)>>1;
				int temp = 0;
				if (arr[start] > arr[middle]) {
					temp = arr[middle];
					arr[middle] = arr[start];
					arr[start] = temp;
				}
				if (arr[start] > arr[end]) {
					temp = arr[end];
					arr[end] = arr[start];
					arr[start] = temp;
				}
				if (arr[middle] > arr[end]) {
					temp = arr[end];
					arr[end] = arr[middle];
					arr[middle] = temp;
				}
				return;
			}
		}else {
			//对数据量大于三的快速排序
			int middle = (start + end)>>1;
			int temp = 0;
			if (arr[start] > arr[middle]) {
				temp = arr[middle];
				arr[middle] = arr[start];
				arr[start] = temp;
			}
			if (arr[start] > arr[end]) {
				temp = arr[end];
				arr[end] = arr[start];
				arr[start] = temp;
			}
			if (arr[middle] > arr[end]) {
				temp = arr[end];
				arr[end] = arr[middle];
				arr[middle] = temp;
			}
			
			//把中间项放到末尾前一个
			temp = arr[middle];
			arr[middle] = arr[end-1];
			arr[end-1] = temp;
			
			//对剩下的排序
			int leftPtr = start,rightPtr = end - 1;
			int indexTemp = 0;
			while(true){
				while(arr[++leftPtr] < temp)
					;
				while(rightPtr > 0 && arr[--rightPtr] >= temp)//解决中值和左值相等的问题rightPtr > 0
					;
				if(leftPtr >= rightPtr){
					break;
				}else {
					indexTemp = arr[leftPtr];
					arr[leftPtr]  = arr[rightPtr];
					arr[rightPtr] = indexTemp;
				}
			}
			
			//把中间值放到正确的位置
			arr[end-1] = arr[leftPtr];
			arr[leftPtr] = temp;
			
			//对左边排序
			quickSort(arr,start,leftPtr-1);
			//对右边排序
			quickSort(arr,leftPtr+1,end);
		}
	}
	
	public static void main(String[] args) {

		System.out.println("快速排序时间复杂度：O(N*logN),效率最好的排序方式");
		int[] arr = new int[1000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*arr.length);
//			arr[i] = i;
		}
		long start = System.currentTimeMillis();
		QuickSort.sort(arr);
		long end = System.currentTimeMillis();
		System.out.println("快速排序耗时："+(end - start));
		
		for (int i = 0; i < 200; i++) {
			System.out.print(arr[i]+" ");
		}

		System.out.println();
	}

}
