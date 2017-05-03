package cn.cherish.mboot.arithmetic;

import java.util.Scanner;

/**
 * 矩阵连乘
 * @author Cherish
 * @version 1.0
 * @date 2017/5/3 18:14
 */
public class MatrixChainMultiplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();// n 个 矩阵
        int[] p = new int[n + 1];// n+1 个数值
        for (int i = 0; i <= n; i++) {
            p[i] = scanner.nextInt();
        }

        int[][] m = new int[n + 1][n + 1];// 舍去 0 行 0列
        int[][] s = new int[n + 1][n + 1];// 舍去 0 行 0列
        fun(p, m, s);

        print(m);
        print(s);
    }

    private static void print(int[][] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void fun(int[] p, int[][] m, int[][] s) {
        int n = p.length - 1;

        // 舍去 0 行 0列
        // 初始化 m 的对角线
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        for (int r = 2; r <= n; r++) {
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;
                m[i][j] = m[i + 1][j] + p[i - 1] * p[i] * p[j];
                s[i][j] = i;

                for (int k = i + 1; k < j; k++) {
                    int t = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (t < m[i][j]) {
                        m[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }


    }

}
