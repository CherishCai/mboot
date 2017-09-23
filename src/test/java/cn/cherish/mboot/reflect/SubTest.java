package cn.cherish.mboot.reflect;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/9/16 13:59
 */
@AnnotationTest(value = "gg", age = 21)
public class SubTest extends ReflectTest {

    public void setF(Float f) {
        this.f = f;
    }

    public Float getF() {
        return f;
    }

    public Float f = 1.1f;

    @AnnotationTest
    public void myF() {
        System.out.println("f = " + f);
    }

    public SubTest(float f) {
        this.f = f;
    }
}
