package com.helloboot.util.base;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lujianhao
 * @date 2018/12/6
 */
/**check*/
public class CharacterUtil {
    /**
     * 判断一个字符是否emoji表情字符
     *
     * @param ch 待检测的字符
     */
    public static boolean isEmoji(char ch) {
        return !((ch == 0x0)
                || (ch == 0x9)
                || (ch == 0xA) || (ch == 0xD)
                || ((ch >= 0x20) && (ch <= 0xD7FF))
                || ((ch >= 0xE000) && (ch <= 0xFFFD))
                || ((ch >= 0x10000) && (ch <= 0x10FFFF)));
    }

    /**
     * 限定非 emoji 的范围判断是否是 emoji 表情
     *
     * @param ch 待检测的字符
     * @return
     */
    public static boolean isNotEmoji(char ch) {
        boolean isLatin = ch >= 0x0020 && ch <= 0x007F;   // 基本拉丁文（字母、数字、基本符号）
        boolean isChinese = ch >= 0x4E00 && ch <= 0x9FCB;  // 基本汉字
        return isChinese || isLatin;
    }

    /**
     * 判断一个字符串中是否包含emoji表情字符
     *
     * @param s 待检测的字符串
     * @return
     */
    public static boolean containEmoji(String s) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        StringBuilder builder = new StringBuilder(s);
        for (int i = 0; i < builder.length(); i++) {
            if (isEmoji(builder.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 清除一个字符串中的emoji表情字符
     *
     * @param s 待检测的字符串
     * @return
     */
    public static String cleanEmoji(String s) {
        if (StringUtils.isEmpty(s)) {
            return s;
        }
        StringBuilder builder = new StringBuilder(s);
        StringBuilder newBuilder = new StringBuilder();
        for (int i = 0; i < builder.length(); i++) {
            char ch = builder.charAt(i);
            if (isNotEmoji(ch)) {
                newBuilder.append(ch);
            }
        }
        return newBuilder.toString().trim();
    }
}
