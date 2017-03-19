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
 * @version Id: Entity.java, v 0.1 2017/3/19 14:14 FancyKong Exp $$
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {
}
