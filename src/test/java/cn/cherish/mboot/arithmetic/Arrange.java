package cn.cherish.mboot.arithmetic;

/**
 * 排列
 */
public class Arrange {

    public static void main(String[] args) {
        char[] data = "ABCDE".toCharArray();
        long start = System.currentTimeMillis();
        permutation(data, 0, 3);
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum);
        System.out.println("耗时 ： " + (end-start));
    }

    private static int sum;

    private static void permutation(char[] data, int j, int k) {

        if (j >= k) {
            for (int i = 0; i < k; i++) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
            ++sum;
            return;
        }

        for (int i = j; i < data.length; i++) {
            {//试探
                char temp = data[j];
                data[j] = data[i];
                data[i] = temp;
            }
            permutation(data, j + 1, k);
            {//回溯
                char temp = data[j];
                data[j] = data[i];
                data[i] = temp;
            }
        }
    }


}
