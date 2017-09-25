package cn.cherish.mboot.arithmetic;

/**
 * n个骰子各点数和出现的概率--动态规划
 * @author Cherish
 * @version 1.0
 * @date 2017/9/25 17:08
 */
public class Dice {
    public static void main(String[] args) {
        fun1(3);
        System.out.println("********** 压缩数组 **********");
        fun2(3);
    }

    public static void fun1(int n) {
        int len = n * 6;
        int[][] arr = new int[n + 1][len + 1];

        for (int i = 1; i <= n; i++) {

            for (int j = 6 * i; j >= i; j--) {
                if (i == 1 || j == i || j == 6 * i) {
                    arr[i][j] = 1;
                } else {
                    for (int k = 1; k <= 6; k++)
                        if (j - k >= i - 1)
                            arr[i][j] += arr[i - 1][j - k];
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= len; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 压缩数组
    public static void fun2(int n) {
        int len = n * 6;
        int[] arr = new int[len + 1];

        for (int i = 1; i <= n; i++) {

            for (int j = 6 * i; j >= i; j--) {
                if (i == 1 || j == i || j == 6 * i) {
                    arr[j] = 1;// 第一行或首末位置
                } else {
                    arr[j] = 0;// 清除上一次留下的遗迹
                    for (int k = 1; k <= 6; k++)
                        if (j - k >= i - 1)
                            arr[j] += arr[j - k];
                }
            }
            arr[i - 1] = 0;

            for (int j = 1; j <= len; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println();
        }

    }
}
