package com.helloboot.util.function;

/**
 * @author lujianhao
 * @date 2019/5/28
 */
public interface Function<S, T> {
    public T apply(S s);
}
