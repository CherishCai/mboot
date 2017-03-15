package cn.cherish.mboot.arithmetic;

/**
 * 排列
 */
public class Arrange {

    public static void main(String[] args) {
        char[] data = "ABCDEFGHIJ".toCharArray();
        long start = System.currentTimeMillis();
        permutation(data, 0);
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum);
        System.out.println("耗时 ： " + (end-start));
    }

    private static int sum;
    private static void permutation(char[] data, int k) {
        if (k >= data.length - 1) {
//            for (int i = 0; i < data.length; i++) {
//                System.out.print(data[i] + " ");
//            }
//            System.out.println();
            ++sum;
            return;
        }

        for (int i = k; i < data.length; i++) {
            {//试探
                char temp = data[k]; data[k] = data[i]; data[i] = temp;
            }
            permutation(data, k + 1);
            {//回溯
                char temp = data[k]; data[k] = data[i]; data[i] = temp;
            }
        }
    }


}
