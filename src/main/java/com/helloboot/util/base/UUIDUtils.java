package com.helloboot.util.base;

import java.util.UUID;

/**
 * @author lujianhao
 * @date 2018/12/5
 */
/**check*/
public class UUIDUtils {
    /**
     * 获取随机 UUID，默认带连字符 「-」
     *
     * @return
     */
    public static String get() {
        return get(true);
    }

    /**
     * 获取随机 UUID
     *
     * @param useHyphen 是否带连字符 「-」
     * @return
     */
    public static String get(boolean useHyphen) {
        if (useHyphen) {
            return UUID.randomUUID().toString();
        }
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtils.get());
        System.out.println(UUID.randomUUID());
    }
}
