package com.helloboot.reflect;

/**
 * @author lujianhao
 * @date 2019/1/24
 */
public class PrivateConstructors {
    public final String string;

    private PrivateConstructors() {
        this(null);
    }

    private PrivateConstructors(String string) {
        this.string = string;
    }
}
