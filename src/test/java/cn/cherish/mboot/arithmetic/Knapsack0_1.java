package cn.cherish.mboot.arithmetic;

import java.util.Scanner;

/**
 * 0-1背包问题，动态规划
 * @author Cherish
 * @version 1.0
 * @date 2017/6/30 23:03
 */
public class Knapsack0_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();// 可选物品数
        int c = sc.nextInt();// 容量
        int[] v = new int[n];// 物品价值
        int[] w = new int[n];// 物品质量

        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
        }

        int[][] m = new int[n][c + 1]; // 解空间
        knapsack(v, w, c, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c + 1; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void knapsack(int[] v, int[] w, int c, int[][] m) {
        int n = v.length - 1;
        int jMax = Math.min(w[n] - 1, c);
        for (int i = 0; i <= jMax; i++) {
            m[n][i] = 0;
        }
        for (int i = w[n]; i <= c; i++) {
            m[n][i] = v[n];
        }

        for (int i = n - 1; i >= 0; i--) {
            jMax = Math.min(w[i] - 1, c);

            for (int j = 0; j <= jMax ; j++) {//容量不够该重量，使用上一次的价值
                m[i][j] = m[i + 1][j];
            }
            for (int j = w[i]; j <= c; j++) {// 没有该物品上一次的价值大，还是加入了该物品的价值大
                m[i][j] = Math.max(m[i + 1][j], m[i + 1][j - w[i]] + v[i]);
            }

        }
    }



}
