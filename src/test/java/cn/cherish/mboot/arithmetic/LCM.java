package cn.cherish.mboot.arithmetic;

/**
 * 最小公倍数，短除法
 * Created by Cherish on 2017/3/11.
 */
public class LCM {

    public static void main(String[] args) {
        int l = lvm(35, 55);
        System.out.println("l = " + l);
    }

    public static int lvm(int a, int b) {
        if (a < 0 || b < 0){
            return -1;
        }
        return a * b / GCD.gcd(a, b);
    }

}
