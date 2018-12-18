package com.helloboot.util.base;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 数学工具类
 * @author lujianhao
 * @date 2018/12/6
 */
public class MathUtils {

    public static final String ALLCHAR
            = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR
            = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR
            = "0123456789";
    /**
     * 获取当前线程的Random
     * @return
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 产生1个[min,max]范围的随机数
     * @param min
     * @param max
     * @return
     */
    public static int integer(int min, int max) {
        return getRandom().nextInt(max + 1 - min) + min;
    }

    /**
     * 产生[min,max]范围的count个不重复的随机数
     * @param min
     * @param max
     * @param count
     * @return
     */
    public static Set<Integer> random(int min, int max, int count){
        Set<Integer> randoms = new HashSet<>();
        int len = max - min + 1;
        if (max < min || count > len) {
            return randoms;
        }
        while (randoms.size() < count) {
            randoms.add(integer(min, max));
        }
        return randoms;
    }

    /**
     * 返回固定长度的数字.
     *
     * @param length
     * @return
     */
    public static String number(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBERCHAR.charAt(getRandom().nextInt(NUMBERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字).
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String string(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(getRandom().nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母).
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String mixString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(getRandom().nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母).
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String lowerString(int length) {
        return mixString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母).
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String upperString(int length) {
        return mixString(length).toUpperCase();
    }

    /**
     * 生成一个定长的纯0字符串.
     *
     * @param length 字符串长度
     * @return 纯0字符串
     */
    public static String zeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0.
     *
     * @param num       数字
     * @param fixdlenth 字符串长度
     * @return 定长的字符串
     */
    public static String toFixdLengthString(int num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(zeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 每次生成的len位数都不相同.
     *
     * @param param
     * @return 定长的数字
     */
    public static int getNotSimple(int[] param, int len) {
        for (int i = param.length; i > 1; i--) {
            int index = getRandom().nextInt(i);
            int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result = result * 10 + param[i];
        }
        return result;
    }

    /**
     * 从指定的数组中随机数组中的某个元素.
     *
     * @param param
     * @return T
     */
    public static <T> T randomItem(T[] param) {
        int index = integer(0, param.length);
        return param[index];
    }

    /**
     * 实现一个简单的字符串乘法.
     *
     * @param str
     * @param multiplication
     * @return
     */
    private static String strMultiplication(String str, int multiplication) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < multiplication; i++) {
            buffer.append(str);
        }
        return buffer.toString();
    }
    /**
     * 返回一个UUID.
     *
     * @return 小写的UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("_", "");
    }

    /**
     * 获取两个数的中间数，包含min和max.
     *
     * @param min 最小
     * @param max 最大
     * @return 中间数
     */
    public static int getMidNum(int min, int max) {
        return min + getRandom().nextInt(max - min + 1);
    }
}
