package cn.cherish.mboot.arithmetic;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/7/28 12:15
 */
public class Combination2 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5};
        //求3个数的组合个数
        combine(data, 0, "", 3);
    }

    private static void combine(int[] data, int i, String str, int k) {
        if (k == 0) {
            System.out.println(str);
            return;
        }
        if (i == data.length) {
            return;
        }
        combine(data, i + 1, str + data[i], k - 1);// 含有该
        combine(data, i + 1, str, k);// 不含有
    }

}
