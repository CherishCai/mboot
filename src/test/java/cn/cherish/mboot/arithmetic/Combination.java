package cn.cherish.mboot.arithmetic;

/**
 * 组合
 */
public class Combination {

    public static void main(String[] args) {
        char[] data = "123456".toCharArray();
//        int sum = combination(data.length, 4);
//        System.out.println("sum = " + sum);
        // C(n,4)
        combine(data, 4);
    }

    private static void combine(char[] data, int m) {
        combination(data, data.length, m, "");
    }

    private static void combination(char[] data, int n, int m, String str) {
        if (n < m) return;
        if (m <= 0) {
            System.out.println(str);
            return;
        }

        combination(data, n - 1, m - 1, str + data[n-1]);// 含有该球
        combination(data, n - 1, m, str);// 不含有
    }

    /**
     * n个里面取m个
     * @param n 球总数
     * @param m 取球数
     * @return 种类数
     */
    private static int combination(int n, int m) {
        if(n < m) return 0;
        if (n == m) return 1;
        if (m <= 0) return 1;

        //假想有一个特殊的球，组合的情况就分为两种，
                // 含有该球，                        不含有
        return combination(n - 1, m - 1) + combination(n - 1, m);
    }


}
