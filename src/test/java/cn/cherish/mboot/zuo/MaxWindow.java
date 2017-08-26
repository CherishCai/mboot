package cn.cherish.mboot.zuo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * 生成窗口最大值数组
 * @author Cherish
 * @version 1.0
 * @date 2017/8/26 12:27
 */
public class MaxWindow {
    public static void main(String[] args) {
        int w = 4;
        int[] arr = new Random().
                ints(10, 0, 100)
                .toArray();
        System.out.println("arr = " + Arrays.toString(arr));
        int[] window = getMaxWindow(arr, w);
        System.out.println("window = " + Arrays.toString(window));
    }

    private static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }

        int[] window = new int[arr.length + 1 - w];
        int index = 0;
        LinkedList<Integer> qMax = new LinkedList<>();// 保留大数值的下标

        for (int i = 0; i < arr.length; i++) {

            // 把小于当前值的都去掉
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]) {
                qMax.pollLast();
            }

            qMax.add(i);// 或许有一天它能成为窗口最大值

            // 去除过期的最大值
            if (qMax.peekFirst() == i - w) {
                qMax.pollFirst();
            }

            if (i >= w - 1) {
                window[index++] = arr[qMax.peekFirst()];// 最大值在第一个位置
            }
        }
        return window;
    }
}
