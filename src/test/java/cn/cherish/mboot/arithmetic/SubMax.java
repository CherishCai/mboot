package cn.cherish.mboot.arithmetic;

import java.util.Scanner;

/**
 * 最大连续子序列和
 * @author Cherish
 * @version 1.0
 * @date 2017/8/26 15:41
 */
public class SubMax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] split = sc.nextLine().trim().split(" ");
        if (split.length == 0) {
            System.out.println(0);
            return;
        }

        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);
        }

        int maxSum = getSubMaxSum(ints, 0, ints.length - 1);
        System.out.println(maxSum);
    }

    private static int getSubMaxSum(int[] ints, int left, int right) {
        if (left >= right)
            return ints[left];

        int middle = (left + right) >> 1;
        int leftSum = getSubMaxSum(ints, left, middle - 1);
        int rightSum = getSubMaxSum(ints, middle + 1, right);

        int max = Math.max(leftSum, rightSum);

        int leftMax = 0x80000000; // 与最大左边连续和
        int leftTmp = 0; // 左边和
        int leftIndex = middle;
        while (--leftIndex >= left) {
            leftTmp += ints[leftIndex];
            if (leftTmp > leftMax) {
                leftMax = leftTmp;
            }
        }

        int rightMax = 0x80000000; // 与最右边连续和
        int rightTmp = 0; // 右边和
        int rightIndex = middle;
        while (++rightIndex <= right) {
            rightTmp += ints[rightIndex];
            if (rightTmp > rightMax) {
                rightMax = rightTmp;
            }
        }
        int middleMax = ints[middle];
        if (rightMax > 0) {
            middleMax += rightMax;
        }
        if (leftMax > 0) {
            middleMax += leftMax;
        }

        return Math.max(max, middleMax);
    }

    /**
     * 还有一种更好的解法，只需要 O（N）的时间。
     * 因为最大 连续子序列和只可能是以位置 0～n-1 中某个位置结尾。
     * 当遍历到第 i 个元素时，判断在它前面的连续子序列和是否大于0，
     * 如果大于 0，则以位置 i 结尾的最大连续子序列和为元素 i 和前门的连续子序列和相加；
     * 否则，则以位置 i 结尾的最大连续子序列和为元素i。
     */
    private static int maxsequence3(int a[], int len) {
        int maxsum, maxhere;
        maxsum = maxhere = a[0];   //初始化最大和为a【0】
        for (int i = 1; i < len; i++) {
            if (maxhere <= 0)
                maxhere = a[i];  //如果前面位置最大连续子序列和小于等于0，则以当前位置i结尾的最大连续子序列和为a[i]
            else
                maxhere += a[i]; //如果前面位置最大连续子序列和大于0，则以当前位置i结尾的最大连续子序列和为它们两者之和
            if (maxhere > maxsum) {
                maxsum = maxhere;  //更新最大连续子序列和
            }
        }
        return maxsum;
    }
}
