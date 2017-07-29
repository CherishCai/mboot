package cn.cherish.mboot.arithmetic;

import java.util.Arrays;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/7/3 13:06
 */
public class Arrange2 {
    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5};
        long start = System.currentTimeMillis();
        // A(n,3)
        arrange1(data, 3);
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
        System.out.println("sum = " + sum);
    }

    private static int sum = 0;

    /**
     * 排列方法1
     * @param data
     * @param k
     */
    private static void arrange1(int[] data, int k) {
        permutation(data, 0, k);
    }
    private static void permutation(int[] data, int j, int k) {
        if (j == k) {
            ++sum;
            // 找到排列，结束本次，输出结果（或者保存下来）
            for (int i = 0; i < j; i++) {
                System.out.print(data[i]);
            }
            System.out.println();
            return;
        }

        for (int i = j; i < data.length; i++) {
            {
                int tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
            }
            permutation(data, j + 1, k);
            {
                int tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
            }
        }
    }

    /**
     * 排列方法2
     * @param data
     * @param k
     */
    private static void arrange2(int[] data, int k){
        f = new boolean[data.length];
        Arrays.fill(f, true);
        count(data, "", k);
    }
    private static boolean[] f;
    private static void count(int[] data, String str, int k) {
        if (k == 0) {
            ++sum;
            System.out.println(str);
            return;
        }
        for (int i = 0; i < data.length; i++) {
            if (!f[i]) {
                continue;
            }
            f[i] = false;
            count(data, str + data[i], k - 1);
            f[i] = true;
        }
    }


}
