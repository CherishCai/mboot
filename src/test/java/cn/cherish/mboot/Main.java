package cn.cherish.mboot;

/**
 * Created by Cherish on 2017/2/13.
 */
public class Main {
    public static void main(String[] args) {
        String java = "java";
        System.out.println("java = " + java.intern() == java);


    }
}