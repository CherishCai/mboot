package cn.cherish.mboot.arithmetic;

/**
 * x 的 y 次方 然后 对 q 取模
 * 思想如下：
 * x*y%q == (x%q) * (y%q) % q
 * Created by Cherish on 2017/3/11.
 */
public class PowerAndDelivery {

    public static void main(String[] args) {
        int pd = pd(2, 7, 17);
        System.out.println("pd = " + pd);
    }

    /**
     * x*x%q == (x%q) * (x) % q
     */
    public static int pd(int x, int y, int q) {
        if (x < 0 || y < 0 || q < 0){
            return -1;
        }
        int r = x;
        for (int i = 1; i < y; i++) {
            r = (x * r) % q;
        }
        return r;
    }

}
