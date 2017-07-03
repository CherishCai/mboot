package cn.cherish.mboot.arithmetic;

import java.util.Scanner;

/**
 * 贪心算法求解背包问题
 * @author Cherish
 * @version 1.0
 * @date 2017/6/6 14:36
 */
public class KnapsackGreedy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();// 输入容量
        int n = sc.nextInt();// 输入物品个数

        float[][] wv = new float[n][2];// 存放物品重量与价值
        for (int i = 0; i < n; i++) {
            wv[i][0] = sc.nextFloat();// 重量wi
            wv[i][1] = sc.nextFloat();// 价值vi
        }

        // 选择排序 v/w 从大到小
        float v_w_max;
        float v_w_tmp;
        int index;
        for (int i = 0; i < n; i++) {
            v_w_max = wv[i][1] /  wv[i][0];
            index = i;
            for (int j = i + 1; j < n; j++) {
                v_w_tmp = wv[j][1] / wv[j][0];
                if (v_w_tmp > v_w_max) {
                    index = j;
                }
            }

            // 交换重量
            v_w_tmp = wv[i][0];
            wv[i][0] = wv[index][0];
            wv[index][0] = v_w_tmp;
            // 交换价值
            v_w_tmp = wv[i][1];
            wv[i][1] = wv[index][1];
            wv[index][1] = v_w_tmp;
        }

        System.out.println("+++++++++++");
        for (int i = 0; i < n; i++) {
            System.out.println(wv[i][0] + " : " + wv[i][1]);
        }
        System.out.println("+++++++++++");

        // 进行贪心选择
        int count = 0;// 装载个数
        for (int i = 0; i < n; i++) {
            if ((c -= wv[i][0]) < 0) {
                break;
            }
            ++count;
        }

        // 使用count 计算总价值，当前重量，被选择的物品
        float sumV = 0;
        float sumW = 0;
        for (int i = 0; i < count; i++) {
            sumW += wv[i][0];
            sumV += wv[i][1];
        }

        System.out.println("sumW = " + sumW);
        System.out.println("sumV = " + sumV);

    }
}
