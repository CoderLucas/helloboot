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
     * Return the first letter of pinyin
     *
     * @param input the chinese characters
     * @return the first letter of pinyin
     */
    public static String getPinyinFirstLetters(final CharSequence input) {
        return getPinyinFirstLetters(input, "", false);
    }

    /**
     * Return the first letter of pinyin
     *
     * @param input       the chinese characters
     * @param separator   the separator
     * @param onlyChinese keep only chinese characters
     * @return the first letter of pinyin
     */
    public static String getPinyinFirstLetters(final CharSequence input, final CharSequence separator, boolean onlyChinese) {
        if (input == null || input.length() == 0) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char anArr = input.charAt(i);
            if (anArr >= 0x4E00 && anArr <= 0x9FA5) {
                output.append(getPinyin(String.valueOf(anArr)).substring(0, 1))
                        .append(separator);
            } else if (!onlyChinese) {
                output.append(anArr)
                        .append(separator);
            }
        }
        return output.toString();
    }

    /**
     * Return the pinyin of the chinese characters
     *
     * @param input the chinese characters
     * @return the pinyin
     */
    public static String getPinyin(final CharSequence input) {
        return getPinyin(input, "", false);
    }

    /**
     * Return the pinyin of the chinese characters
     *
     * @param input       the chinese characters
     * @param separator   the separator
     * @param onlyChinese keep only chinese characters
     * @return the pinyin
     */
    public static String getPinyin(final CharSequence input, final CharSequence separator, boolean onlyChinese) {
        if (input == null || input.length() == 0) {
            return "";
        }
        StringBuilder output = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        try {
            for (int i = 0; i < input.length(); i++) {
                char anArr = input.charAt(i);
                if (anArr >= 0x4E00 && anArr <= 0x9FA5) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(anArr, format);
                    output.append(temp[0])
                            .append(separator);
                } else if (!onlyChinese) {
                    output.append(Character.toString(anArr))
                            .append(separator);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
