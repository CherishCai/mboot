package cn.cherish.mboot.arithmetic;

import java.util.Arrays;
import java.util.Random;

/**
 * 最大连续子序列和
 * @author Cherish
 * @version 1.0
 * @date 2017/8/26 18:35
 */
public class MaxContinuousSub {
    public static void main(String[] args) {
        int[] ints = new Random()
                .ints(10, -10, 10)
                .toArray();
        System.out.println("ints = " + Arrays.toString(ints));
        int m = maxSequence3(ints);
        System.out.println("m = " + m);
    }

    /**
     * 还有一种更好的解法，只需要 O（N）的时间。
     * 因为最大 连续子序列和只可能是以位置 0～n-1 中某个位置结尾。
     * 当遍历到第 i 个元素时，判断在它前面的连续子序列和是否大于0，
     * 如果大于 0，则以位置 i 结尾的最大连续子序列和为元素 i 和前门的连续子序列和相加；
     * 否则，则以位置 i 结尾的最大连续子序列和为元素i。
     */
    private static int maxSequence3(int ints[]) {
        int maxSum, curSum;
        maxSum = curSum = ints[0];   //初始化最大和为a【0】
        for (int i = 1; i < ints.length; i++) {
            if (curSum <= 0)
                //如果前面位置最大连续子序列和小于等于0，则以当前位置i结尾的最大连续子序列和为a[i]
                curSum = ints[i];
            else
                //如果前面位置最大连续子序列和大于0，则以当前位置i结尾的最大连续子序列和为它们两者之和
                curSum += ints[i];

            if (curSum > maxSum) {
                maxSum = curSum;  //更新最大连续子序列和
            }// maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}
