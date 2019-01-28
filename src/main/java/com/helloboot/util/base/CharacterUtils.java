package com.helloboot.util.base;

/**
 * @author lujianhao
 * @date 2018/12/6
 */
public class CharacterUtils {

    /**
     * Determine if the character is emoji
     *
     * @param ch the character
     * @return the boolean result
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
     * Determine if the character is not emoji
     *
     * @param ch the character
     * @return the boolean result
     */
    public static boolean isNotEmoji(char ch) {
        boolean isLatin = ch >= 0x0020 && ch <= 0x007F;   // 基本拉丁文（字母、数字、基本符号）
        boolean isChinese = ch >= 0x4E00 && ch <= 0x9FCB;  // 基本汉字
        return isChinese || isLatin;
    }

    /**
     * Determine if the string contains emoji
     *
     * @param input the character
     * @return the boolean result
     */
    public static boolean containEmoji(CharSequence input) {
        if (input == null || input.length() == 0) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (isEmoji(input.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clear emoji in the string
     *
     * @param input the character
     * @return the string after clean emoji
     */
    public static String cleanEmoji(CharSequence input) {
        if (input == null || input.length() == 0) {
            return String.valueOf(input);
        }
        StringBuilder newBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (isNotEmoji(ch)) {
                newBuilder.append(ch);
            }
        }
        return newBuilder.toString().trim();
    }
}
