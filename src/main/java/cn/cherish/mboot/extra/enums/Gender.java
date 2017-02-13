/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package cn.cherish.mboot.extra.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Jiangjiaze
 * 性别枚举类
 * @version Id: Gender.java, v 0.1 2016/9/27 0027 下午 4:17 FancyKong Exp $$
 */
@Getter
@AllArgsConstructor
public enum Gender {
    M("M", "男"),
    F("F", "女"),
    N("N", "未知");

    private String code;

    private String desc;
}
