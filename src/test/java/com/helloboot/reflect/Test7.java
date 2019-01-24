package com.helloboot.reflect;

/**
 * @author lujianhao
 * @date 2019/1/24
 */
public class Test7 {
    public final String  s;
    public final Integer i;

    private Test7(int i) {
        this(null, i);
    }

    private Test7(String s) {
        this(s, null);
    }

    private Test7(String s, int i) {
        this(s, (Integer) i);
    }

    private Test7(String s, Integer i) {
        this.s = s;
        this.i = i;
    }
}
