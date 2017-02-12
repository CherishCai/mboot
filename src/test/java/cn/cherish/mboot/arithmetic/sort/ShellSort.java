package cn.cherish.mboot.arithmetic.sort;

public class ShellSort {

	/**
	 * 希尔排序
	 * @param arr->整型数组
	 * @return int[]->整型数组
	 */
	public static int[] sort(int[] arr){
		int temp = 0;
		int inner = 0,outer = 0;
		
		int h = 1;
		while(3*h < arr.length){
			h = 3*h + 1;
		}
		
		while(h > 0){
			for(outer = h; outer < arr.length; outer++){
				temp = arr[outer];
				inner = outer;
				
				while(inner > h-1 && arr[inner-h] >= temp){
					arr[inner] = arr[inner-h];
					inner -= h;
				}
				arr[inner] = temp;
				
			}//end for
			h = (h - 1)/3;
		}//end while
		
		return arr;
	}
	
	public static void main(String[] args) {

		System.out.println("希尔排序时间复杂度：O(N*logN^2),效率挺好");
		int[] arr = new int[1000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*arr.length);
//			arr[i] = i;
		}
		long start = System.currentTimeMillis();
		ShellSort.sort(arr);
		long end = System.currentTimeMillis();
		System.out.println("希尔排序耗时："+(end - start));
		
		for (int i = 0; i < 200; i++) {
			System.out.print(arr[i]+" ");
		}

		System.out.println();
	}

}
