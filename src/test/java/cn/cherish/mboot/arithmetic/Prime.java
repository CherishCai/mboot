package cn.cherish.mboot.arithmetic;


/**
 * 质数判断
 * @author Cherish
 * @version 1.0
 * @date 2017/7/31 17:35
 */
public class Prime {
    public static void main(String[] args) {
        int num = 11;
        boolean result = isPrime(num);
        System.out.println("result = " + result);

        int number = 50;
        int count = 0;
        boolean notPri[] = new boolean[number + 1];// 筛法确定质数数组
        for (int i = 2; i <= number; i++) {
            if (!notPri[i]) //如果当前数字是质数，筛掉所有该数的倍数
                for (int j = 2; j * i <= number; j++) {
                    notPri[i * j] = true;
                }
        }

        for (int i = 2; i < number - 1; i++) {
            for (int j = i; j < number; j++) {
                int remain = number - i - j;
                if (remain >= 1 && remain <= number && remain >= i && remain >= j &&
                        !notPri[i] && !notPri[j] && !notPri[remain]) {
                    System.out.println(i + "," + j + "," + remain);
                    if (i == j && i == remain)
                        count += 1;
                    else if (i == remain || i == j || j==remain) {
                        count += 3;
                    } else {
                        count += 6;
                    }
                }
            }
        }
        System.out.println(count);
    }

    private static boolean isPrime(long num) {
        if (2 == num) {
            return true;
        }
        if (num < 2 || num % 2 == 0) {
            return false;
        }

        for (long i = 3; i * i < num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
