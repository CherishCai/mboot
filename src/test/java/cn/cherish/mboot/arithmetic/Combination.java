package cn.cherish.mboot.arithmetic;

/**
 * 组合
 */
public class Combination {

    public static void main(String[] args) {
        char[] data = "123456".toCharArray();
        // C(n,4)
        int sum = combination(data.length, 4);
        System.out.println("sum = " + sum);
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
