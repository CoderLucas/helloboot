package com.helloboot.util.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author lujianhao
 * @date 2018/12/17
 */
public final class PinyinUtils {
    private PinyinUtils() {
        throw new UnsupportedOperationException("You can't instantiate PinyinUtils...");
    }

    /**
     * 获取汉字串拼音首字母，英文字符不变.
     *
     * @param input the chinese string
     * @return 汉语拼音首字母
     */
    public static String getPinyinFirstLetters(final String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        int len = input.length();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < len; i++) {
            output.append(getPinyin(String.valueOf(input.charAt(i))).substring(0,1));
        }
        return output.toString();
    }

    /**
     * 将字符串中的中文转化为拼音,其他字符不变.
     *
     * @param input the chinese string
     * @return 汉语拼音首字母
     */
    public static String getPinyin(final String input) {
        char[] arr = input.trim().toCharArray();
        StringBuilder output = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        try {
            for (char anArr : arr) {
                if (Character.toString(anArr).matches("^[\\u4e00-\\u9fa5]+$")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(anArr, format);
                    output.append(temp[0]);
                } else {
                    output.append(Character.toString(anArr));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
