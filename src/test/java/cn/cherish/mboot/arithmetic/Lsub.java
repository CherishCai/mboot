package cn.cherish.mboot.arithmetic;

import java.util.Scanner;

/**
 * 最长单调递增子序列
 *
 * @author Cherish
 * @version 1.0
 * @date 2017/5/19 10:44
 */
public class Lsub {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = scanner.nextInt();
        }

        int[][] tmp = new int[n + 1][n + 1];
        // 舍去 0 行 0列
        // 初始化 m 的对角线
        for (int i = 1; i <= n; i++) {
            tmp[i][i] = 1;
        }

        for (int r = 2; r <= n; r++) {
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;

                if (arr[j - 1] < arr[j]) {
                    tmp[i][j] = tmp[i][j - 1] + 1;
                } else {
                    tmp[i][j] = Math.max(tmp[i][j - 1], tmp[i + 1][j]);
                }
            }
        }

        // 模样
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(tmp[i][j] + " ");
            }
            System.out.println();
        }

        // 长度
        int len = tmp[1][n];
        System.out.println("len = " + len);

        // 构造最优解, 分析第一行, 可能是多解
        int[] index = new int[len + 1];
        int l = n;
        index[0] = len;
        for (int i = n; i > 1; i--) {
            if (tmp[1][i] > tmp[1][i - 1]) {
                l = i;//随后一个位置
                break;
            }
        }
        int t = tmp[1][l];
        index[len] = l;
        int ll = len - 1;
        for (int i = l; i > 1; i--) {
            if (tmp[1][i] > tmp[1][i - 1]) {
                index[ll--] = i - 1;
                if (ll == 0) {
                    break;
                }
            }
        }
        for (int i = 0; i <= len; i++) {
            System.out.print(index[i] + " ");
        }

    }
}
