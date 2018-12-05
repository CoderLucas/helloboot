package com.helloboot.util.annotation;

import java.lang.annotation.*;

/**
 * 通过锁的方式实现方法串行化执行的注解
 * @author lujianhao
 * @date 2018/11/30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SerializabledWithLock {
    /**
     * 锁的key(支持SpEL,动态参数解析)
     * @return
     */
    String key() default "";

    /**
     * 尝试获取锁超时时间，单位:毫秒，默认:60秒
     * @return
     */
    long timeout() default 60000;
}
