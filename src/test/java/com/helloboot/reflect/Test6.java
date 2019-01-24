package com.helloboot.reflect;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lujianhao
 * @date 2019/1/24
 */
public class Test6 {
    public Map<String, String> map = new HashMap<String, String>();

    public void put(String name, String value) {
        map.put(name, value);
    }
}
