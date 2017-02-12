package cn.cherish.mboot.arithmetic.sort;

public class MergeSort {

	/**
	 * 归并排序
	 * @param arr
	 * @return
	 */
	public static int[] sort(int[] arr){
		int[] newArray = new int[arr.length];
		merge(arr,0,arr.length-1,newArray);
		
		return newArray;
	}
	/**
	 * 执行归并
	 * @param arr
	 * @param left
	 * @param right
	 * @param newArray
	 */
	protected static void merge(int[] arr, int left, int right, int[] newArray) {
		if (left >= right) {
			return;
		}
		int middle = (left + right)>>1;//除二
		merge(arr, left, middle, newArray);//先把左半有序
		merge(arr, middle+1, right, newArray);//再把右半有序
		
		//对目前的排序
		int n = left,increaseLeft = left,increaseRight = middle+1;
		while(n <= right){
			int leftTemp = Integer.MAX_VALUE,rightTemp = Integer.MAX_VALUE;
			if (increaseLeft <= middle) {
				leftTemp = arr[increaseLeft];
			}
			if (increaseRight <= right) {
				rightTemp = arr[increaseRight];
			}
			if (leftTemp < rightTemp) {
				newArray[n++] = leftTemp;
				increaseLeft++;
			}else {
				newArray[n++] = rightTemp;
				increaseRight++;
			}
			
		}//end while
		
		//赋值回原来的数组
		while(right >= left){
			arr[right] = newArray[right];
			right--;
		}
	}

	public static void main(String[] args) {
		System.out.println("归并排序时间复杂度：O(N*logN),非常好的效率，只是需要两倍的内存空间");
		int[] arr = new int[1000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*arr.length);
		}
		long start = System.currentTimeMillis();
		arr = MergeSort.sort(arr);
		long end = System.currentTimeMillis();
		System.out.println("归并排序耗时："+(end - start));
		
		for (int i = arr.length>>8; i < arr.length>>7; i++) {
			System.out.print(arr[i]+" ");
		}

		System.out.println();
	}

}
