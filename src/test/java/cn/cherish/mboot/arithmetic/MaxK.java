package cn.cherish.mboot.arithmetic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 第 K 大的数
 * @author Cherish
 * @version 1.0
 * @date 2017/8/26 16:18
 */
public class MaxK {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] split = sc.nextLine().trim().split(" ");
        if (split.length == 0) {
            return;
        }

        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }

        int k = sc.nextInt();
        if (k < 1 || k > ints.length) {
            return;
        }

//        Arrays.sort(ints);
//        System.out.println(ints[ints.length - k]);
        // 第 k 大
        int result = getMaxK(ints, 0, ints.length - 1, k - 1);
        System.out.println(result);
    }

    private static int getMaxK(int[] ints, int left, int right, int k) {
        System.out.println("Arrays.toString(ints) = " + Arrays.toString(ints));
        System.out.println("k = " + k);
        if (left >= right) {
            return ints[left];
        }

        int index = partition(ints, left, right);
        if (index == k) {
            return ints[index];
        }

        if (index > k) {
            return getMaxK(ints, left, index - 1, k);
        } else {
            return getMaxK(ints, index + 1, right, k - index - 1);
        }
    }

    private static int partition(int[] ints, int left, int right) {
        int pivot = ints[left];
        int leftIndex = left + 1;

        while (leftIndex < right) {
            while (ints[leftIndex] > pivot && leftIndex < right) {
                leftIndex++;
            }
            while (ints[right] < pivot && leftIndex < right) {
                right--;
            }
            int tmp = ints[leftIndex];
            ints[leftIndex] = ints[right];
            ints[right] = tmp;
        }
        if (ints[leftIndex] < pivot) {
            leftIndex--;
        }
        System.out.println("partition = " + Arrays.toString(ints));

        ints[left] = ints[leftIndex];
        ints[leftIndex] = pivot;
        System.out.println("partition2 = " + Arrays.toString(ints));

        return leftIndex;
    }
}
