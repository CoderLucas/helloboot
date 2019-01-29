package com.helloboot;

import com.helloboot.util.base.MathUtils;
import org.junit.Test;

/**
 * @author lujianhao
 * @date 2019/1/29
 */
public class MathUtilsTest {

    private static int[] number = {1, 2, 3, 4, 5, 6};

    @Test
    public void random() {
        System.out.println(MathUtils.random(0, 100));
        System.out.println(MathUtils.random(0, 100, 10));
        System.out.println(MathUtils.number(10));
        System.out.println(MathUtils.numberAndString(10));
        System.out.println(MathUtils.string(10));
        System.out.println(MathUtils.lowerString(10));
        System.out.println(MathUtils.upperString(10));
        System.out.println(MathUtils.zeroString(10));
        System.out.println(MathUtils.toFixdLengthString(123456, 10));
        System.out.println(MathUtils.strMultiplication("abcd", 2));
        System.out.println(MathUtils.uuid());
    }
}
