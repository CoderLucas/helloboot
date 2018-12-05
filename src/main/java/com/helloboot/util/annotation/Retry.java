package com.helloboot.util.annotation;

import java.lang.annotation.*;

/**
 * 重试注解(使用时注意AOP嵌套问题,并且会吞没异常)
 *
 * @author lujianhao
 * @date 2018/11/30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Retry {
    /**
     * 重试次数
     * @return
     */
    int retryTimes() default 3;

    /**
     * 重试间隔毫秒数
     * @return
     */
    long delayInMillis() default 50L;
}
