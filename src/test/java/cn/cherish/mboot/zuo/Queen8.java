package cn.cherish.mboot.zuo;

/**
 * 左神解法，n皇后问题
 * @author Cherish
 * @version 1.0
 * @date 2017/8/25 15:23
 */
public class Queen8 {
    public static void main(String[] args) {
        int n = 8;
        int result = qNum(n);
        System.out.println("result = " + result);
    }

    private static int qNum(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return num(record, 0, n);
    }

    private static int num(int[] record, int i, int n) {
        if (i == n) {
            return 1;
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += num(record, i + 1, n);
            }

        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }
}
