package com.helloboot.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lujianhao
 * @date 2018/12/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Excel {
    /**
     * 列名
     */
    String name() default "";

    /**
     * 宽度
     */
    int width() default 25;

    /**
     * 忽略该字段
     */
    boolean skip() default false;

    /**
     * 日期格式
     */
    String dateFormat() default "yyyy年MM月dd日 HH:mm:ss";
}
