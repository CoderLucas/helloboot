package com.helloboot.reflect;

/**
 * @author lujianhao
 * @date 2019/1/24
 */
public class Test3 {
    public Object     n;
    public MethodType methodType;

    public void method() {
        this.n = null;
        this.methodType = MethodType.NO_ARGS;
    }

    public void method(Integer n1) {
        this.n = n1;
        this.methodType = MethodType.INTEGER;
    }

    public void method(Number n1) {
        this.n = n1;
        this.methodType = MethodType.NUMBER;
    }

    public void method(Object n1) {
        this.n = n1;
        this.methodType = MethodType.OBJECT;
    }

    public static enum MethodType {
        NO_ARGS,
        INTEGER,
        NUMBER,
        OBJECT
    }
}
