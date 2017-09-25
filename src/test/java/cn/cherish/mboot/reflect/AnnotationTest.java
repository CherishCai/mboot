package cn.cherish.mboot.reflect;

import java.lang.annotation.*;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/9/16 14:40
 */
@Documented
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {

    String value() default "";

    int age() default 18;

}
