package cn.cherish.mboot.arithmetic; /**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * @author Jiangjiaze
 * @version Id: Shudu.java, v 0.1 2017/2/12 10:03 FancyKong Exp $$
 */
public class Sudoku {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("F:\\sudoku.txt"));

        long start = System.currentTimeMillis();
        int n[][] = new int[9][9];
        boolean row[][] = new boolean[9][10]; //这个表示有9行,每一行中已经被选中的数字作为下标为true
        //比如, 第一行有5 和 3 那么 row[0][5] = true  row[0][3] = true;
        boolean column[][] = new boolean[9][10]; //表示列
        boolean color[][] = new boolean[9][10]; //表示每个3*3格子
        for (int i = 0; i < n.length; i++) {
            String s = sc.next();
            for (int j = 0; j < n.length; j++) {
                n[i][j] = Integer.valueOf("" + s.charAt(j));
                if (n[i][j] > 0) {
                    row[i][n[i][j]] = true;
                    column[j][n[i][j]] = true;
                    color[3 * (i / 3) + j / 3][n[i][j]] = true;
                }//给上面三个数组赋值
            }
        }
        f(row, column, color, 0, 0, n);
        System.out.println(System.currentTimeMillis()-start);
    }

    /**
     * 给矩阵每个元素填数,没执行一次f,填一个数,填到末尾自动换行
     * @param row 行
     * @param column 列
     * @param color 3*3
     * @param r 当前所在的行
     * @param c 当前所在的列
     * @param n 当前矩阵的样子
     */
    private static void f(boolean[][] row, boolean[][] column, boolean[][] color, int r, int c, int[][] n) {
        if (r == 9 && c == 0) {
            print(n);
            System.out.println();
            return;
        }
        if (n[r][c] != 0) {
            if (c < 8)
                f(row, column, color, r, c + 1, n);
            else
                f(row, column, color, r + 1, 0, n);//换行
        } else
            for (int i = 1; i < 10; i++) {
                if (row[r][i] || column[c][i] ||color[3*(r/3)+c/3][i])
                    continue;
                row[r][i] = true;
                column[c][i] = true;
                color[3*(r/3)+c/3][i] = true;
                n[r][c] = i;
                if (c < 8)
                    f(row, column, color, r, c + 1, n);
                else
                    f(row, column, color, r + 1, 0, n);//换行
                n[r][c] = 0;
                row[r][i] = false;
                column[c][i] = false;
                color[3*(r/3)+c/3][i] = false;
            }
    }

    private static void print(int[][] n) {
        for (int[] as : n) {
            for(int a : as){
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

}
