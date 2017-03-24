package cn.cherish.mboot.arithmetic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 字符串的类型数目，可以换任意位置而达到相通的字符串判定为一种类型
 * @author Cherish
 * @version Id: Main.java, v 1 2017/3/23 19:47 Cherish Exp $$
 */
public class StringType {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        //舍去零下标
        String[] strings = new String[51];
        for(int i = 1; i <= N; i++){
            strings[i] = sc.next();
        }

        int[][] cache = new int[51][N+1];
        for (int i = 1; i <= N; i++) {
            String s = strings[i];
            int strLen = s.length();
            int oldNum = cache[strLen][0];

            cache[strLen][0]++;
            cache[strLen][oldNum + 1] = i;//保留strings 的下标
        }

        int count = 0;
        for (int i = 1; i <= 50; i++) {
            int[] ints = cache[i];

            if (ints[0] == 0)
                continue;
            ++count;//有则加一

            int anInt1 = ints[1];
            String string1 = strings[anInt1];
            char[] chars1 = string1.toCharArray();
            Arrays.sort(chars1);

            for (int j = 2; j <= ints[0]; j++) {
                int anInt = ints[j];

                String string = strings[anInt];
                char[] chars = string.toCharArray();
                Arrays.sort(chars);
                if (!Arrays.equals(chars1, chars))
                    ++count;
            }
        }
        System.out.println(count);
    }

}
