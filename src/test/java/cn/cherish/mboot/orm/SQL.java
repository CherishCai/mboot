/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package cn.cherish.mboot.orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jiangjiaze
 * @version Id: SQL.java, v 0.1 2017/3/17 16:53 FancyKong Exp $$
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SQL {
    String value() default "";
    String type() default "select";
}
