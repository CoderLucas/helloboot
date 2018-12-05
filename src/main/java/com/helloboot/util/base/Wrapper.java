package com.helloboot.util.base;

/**
 * @author lujianhao
 * @date 2018/12/5
 */
public class Wrapper<T> {
    private T value;

    public Wrapper(){}

    public Wrapper(T value){
        this.value = value;
    }

    public T get(){
        return value;
    }

    public void set(T value){
        this.value = value;
    }
}
