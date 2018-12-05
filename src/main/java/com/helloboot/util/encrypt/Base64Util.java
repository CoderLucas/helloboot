package com.helloboot.util.encrypt;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * Base64 相关工具类
 *
 * @author lujianhao
 * @date 2018/12/5
 */
public class Base64Util {
    /**
     * Base64 URL 安全编码
     *
     * @param url 编码字符串
     * @return
     */
    public static String encodeUrl(String url) {
        if (StringUtils.isNotEmpty(url)) {
            return Base64.encodeBase64URLSafeString(url.getBytes());
        }
        return url;
    }

    /**
     * Base64 URL 安全解码
     *
     * @param encoded 需要解码字符串
     * @return
     */
    public static String decodeUrl(String encoded) {
        if (StringUtils.isNotEmpty(encoded)) {
            return new String(new Base64(true).decode(encoded));
        }
        return encoded;
    }
}
