package cn.cherish.mboot.arithmetic.sort;

import java.util.Arrays;

/**
 * Created by Cherish on 2017/3/12.
 */
public class SortTest {
    public static void main(String[] args) {
        int[] arr = new int[1000_00];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * arr.length);
        }
        System.out.println("初始状态：");
        show(arr);

        System.out.println("============DualPivotQuicksort================");
        int[] ArraysInts = Arrays.copyOf(arr, arr.length);
        long start = System.currentTimeMillis();
        Arrays.sort(ArraysInts);
        long end = System.currentTimeMillis();
        show(ArraysInts);
        System.out.println("DualPivotQuicksort耗时： " + (end-start));

        System.out.println("============HeapSort================");
        int[] heapInts = Arrays.copyOf(arr, arr.length);
        start = System.currentTimeMillis();
        int[] heapSort = HeapSort.sort(heapInts);
        end = System.currentTimeMillis();
        show(heapSort);
        System.out.println("HeapSort耗时： " + (end-start));

        System.out.println("============QuickSort================");
        int[] quickInts = Arrays.copyOf(arr, arr.length);
        start = System.currentTimeMillis();
        int[] quickSort = QuickSort.sort(quickInts);
        end = System.currentTimeMillis();
        show(quickSort);
        System.out.println("QuickSort耗时： " + (end-start));

        System.out.println("============MergeSort================");
        int[] mergeInts = Arrays.copyOf(arr, arr.length);
        start = System.currentTimeMillis();
        int[] mergeSort = MergeSort.sort(mergeInts);
        end = System.currentTimeMillis();
        show(mergeSort);
        System.out.println("MergeSort耗时： " + (end-start));

        System.out.println("============ShellSort================");
        int[] shellInts = Arrays.copyOf(arr, arr.length);
        start = System.currentTimeMillis();
        int[] shellSort = ShellSort.sort(shellInts);
        end = System.currentTimeMillis();
        show(shellSort);
        System.out.println("ShellSort耗时： " + (end-start));

        System.out.println("============InsertSort================");
        int[] insertInts = Arrays.copyOf(arr, arr.length);
        start = System.currentTimeMillis();
        int[] insertSort = InsertSort.sort(insertInts);
        end = System.currentTimeMillis();
        show(insertSort);
        System.out.println("InsertSort耗时： " + (end-start));

        System.out.println("============SelectSort================");
        int[] selectInts = Arrays.copyOf(arr, arr.length);
        start = System.currentTimeMillis();
        int[] selectSort = SelectSort.sort(selectInts);
        end = System.currentTimeMillis();
        show(selectSort);
        System.out.println("SelectSort耗时： " + (end-start));

        System.out.println("============BubbleSort================");
        int[] bubbleInts = Arrays.copyOf(arr, arr.length);
        start = System.currentTimeMillis();
        int[] bubbleSort = BubbleSort.sort(bubbleInts);
        end = System.currentTimeMillis();
        show(bubbleSort);
        System.out.println("BubbleSort耗时： " + (end-start));


    }

    static void show(int[] arr){
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(" " + arr[i]);
//        }
        System.out.println();
    }
}
