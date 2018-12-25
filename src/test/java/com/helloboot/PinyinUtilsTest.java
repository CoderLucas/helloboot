package com.helloboot;

import com.helloboot.util.pinyin.PinyinUtils;
import org.junit.Test;

/**
 * @author lujianhao
 * @date 2018/12/25
 */
public class PinyinUtilsTest {
    @Test
    public void pinyin() {
        System.out.println(PinyinUtils.getPinyin("测试Lucas123"));
        System.out.println(PinyinUtils.getPinyinFirstLetters("测试Lucas123"));
    }
}
