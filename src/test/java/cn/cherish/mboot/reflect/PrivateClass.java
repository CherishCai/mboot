package cn.cherish.mboot.reflect;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/9/16 14:23
 */
public class PrivateClass {
    private PrivateClass() {

    }

    public void print(){
        System.out.println(getClass());
    }

    private Object pp(){
        System.out.println("private method");
        return this;
    }
}
