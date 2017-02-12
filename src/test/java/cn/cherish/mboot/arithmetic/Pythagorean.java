package cn.cherish.mboot.arithmetic;

public class Pythagorean {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //要使用long类型，亲测坑了，因为 int = int*int，数据大的话，就爆了
        long n = 10_000_000;
        int times = 0;
        long m = (long)( n / Math.sqrt(2));
        for (long i = 1; i <= m; i++) {
            double sqrtB = Math.sqrt(n * n - i * i);
            if (  (sqrtB == (long)sqrtB)  ){
                System.out.println("A = " + i+"   B = " + (long)sqrtB);
                times++;
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("(end - start) = " + (end - start));
        System.out.println(times);
    }

}
