package cn.cherish.mboot.arithmetic;


/**
 * 最大公约数,辗转相除法
 * Created by Cherish on 2017/3/11.
 */
public class GCD {

    public static int gcd(int a, int b){
        if (a < 0 || b <0){
            return -1;
        }
        //辗转相除法
        return divisionAlgorithm(b, a % b);
    }

    private static int divisionAlgorithm(int a, int b) {
        if (b == 0)
            return a;
        return divisionAlgorithm(b, a % b);
    }

    public static void main(String[] args) {
        int g = gcd(40, 15);
        System.out.println("g = " + g);
    }
}
