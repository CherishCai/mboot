package cn.cherish.mboot.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/4/27 21:07
 */
public class Combination2 {
    public static void main(String[] args) {

        int n = 10;
        int[] indexs = new int[n];
        for (int i = 0; i < n; i++) {
            indexs[i] = i;
        }

        List<int[]> list = combine(indexs, 3);

        list.forEach(ints -> {
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        });

    }

    public static List<int[]> combine(int[] a, int m) {
        int n = a.length;
        List<int[]> result = new ArrayList<>();
        if (m > n) {
            return result;
        }

        int[] bs = new int[n];
        for (int i = 0; i < n; i++) {
            bs[i] = 0;
        }
        //初始化
        for (int i = 0; i < m; i++) {
            bs[i] = 1;
        }
        boolean flag = true;
        boolean tempFlag = false;
        int pos = 0;
        int sum = 0;
        do {
            sum = 0;
            pos = 0;
            tempFlag = true;
            result.add(getIndex(bs, a, m));

            for (int i = 0; i < n - 1; i++) {
                if (bs[i] == 1 && bs[i + 1] == 0) {
                    bs[i] = 0;
                    bs[i + 1] = 1;
                    pos = i;
                    break;
                }
            }
            for (int i = 0; i < pos; i++) {
                if (bs[i] == 1) {
                    sum++;
                }
            }
            for (int i = 0; i < pos; i++) {
                if (i < sum) {
                    bs[i] = 1;
                } else {
                    bs[i] = 0;
                }
            }
            for (int i = n - m; i < n; i++) {
                if (bs[i] == 0) {
                    tempFlag = false;
                    break;
                }
            }
            if (!tempFlag) {
                flag = true;
            } else {
                flag = false;
            }

        } while (flag);
        result.add(getIndex(bs, a, m));

        return result;
    }

    private static int[] getIndex(int[] bs, int[] a, int m) {
        int[] result = new int[m];
        int pos = 0;
        for (int i = 0; i < bs.length; i++) {
            if (bs[i] == 1) {
                result[pos] = a[i];
                pos++;
            }
        }
        return result;
    }
}
