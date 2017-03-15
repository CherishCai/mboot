package cn.cherish.mboot;

/**
 * Created by Cherish on 2017/3/10.
 */
public enum Weekend {
    STA, SUN;

    public static void main(String[] args) {
        Weekend sun = Weekend.valueOf("SUN");
        System.out.println("sun = " + sun);
    }

}
