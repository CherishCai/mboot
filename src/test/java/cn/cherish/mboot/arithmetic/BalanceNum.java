package cn.cherish.mboot.arithmetic;

import java.util.Scanner;

/**
 * 平衡数
 * @author Cherish
 * @version Id: BalanceNum.java, v 1 2017/3/23 19:32 Cherish Exp $$
 */
public class BalanceNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String numStr = sc.next();

        for (int i = 1; i < numStr.length(); i++) {
            String a = numStr.substring(0, i);
            String b = numStr.substring(i);
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            if (f(a,b)){
                System.out.println("YES");
                return;
            }

        }

        System.out.println("NO");

    }

    private static boolean f(String a, String b) {
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        int sumA = 1;
        int sumB = 1;
        for (int i = 0; i < charsA.length; i++) {
            Integer integerA = (int) (charsA[i]-'0');
            System.out.println("integerA = " + integerA);
            sumA *= integerA;
        }
        for (int i = 0; i < charsB.length; i++) {
            Integer integerB = (int) (charsB[i]-'0');
            System.out.println("integerB = " + integerB);
            sumB *= integerB;
        }
        System.out.println("sumA = " + sumA);
        System.out.println("sumB = " + sumB);

        return sumA == sumB;
    }

}
