package cn.cherish.mboot.arithmetic;

import java.util.Arrays;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/7/29 15:16
 */
public class Combination3 {
    public static void main(String[] args) {
        int[] data = new int[]{1,2,3,4,5};

        combinate(data, 3);
        System.out.println("sum = " + sum);
    }

    private static void combinate(int[] data, int k) {
        tmp = new int[k];
        c(data, 0, k);
    }

    private static int sum = 0;
    private static int[] tmp;
    private static int index = 0;// tmp数组的下标
    private static void c(int[] data, int i, int k) {
        if (k == 0){
            System.out.println(Arrays.toString(tmp));
            sum++;
            return;
        }
        if (i == data.length) {// 已经没有可选的元素
            return;
        }

        // 含有 第 i 位
        tmp[index++] = data[i];
        c(data, i + 1,k - 1);
        index--;

        c(data, i + 1, k);// 不含有 第 i 位

    }
}
