package com.helloboot.util.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 两个请求，先后执行同一个被注解的方法，参数构成的key相同时
 * 则前一个请求正在执行+interval（用于方法中用了多线程）的时间内，执行后一个请求会直接返回ErrorCodeEnum.EC_HIGH_FREQUENCY
 * @author lujianhao
 * @date 2018/11/30
 */
//@Target(ElementType.METHOD)
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
public @interface HighFrequencyBarrier {
    String[] keys() default {};
    int keyDeep() default 1;

    String group() default "";

    long interval() default 0;
    TimeUnit unit() default TimeUnit.MILLISECONDS;

//    ErrorCodeEnum errorCode() default ErrorCodeEnum.EC_HIGH_FREQUENCY;
    String message() default "";
}
