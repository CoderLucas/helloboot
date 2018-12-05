package com.helloboot.util.web;

import com.helloboot.util.system.Server;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author lujianhao
 * @date 2018/12/5
 */
public class WebUtil {
    /**
     * 编码URL
     *
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        if (url != null && url.length() > 0) {
            try {
                return URLEncoder.encode(url, Server.CHARSET);
            } catch (Throwable e) {
            }
        }

        return url;
    }

    /**
     * 解码URL
     *
     * @param url
     * @return
     */
    public static String decodeUrl(String url) {
        if (url != null && url.length() > 0) {
            try {
                return URLDecoder.decode(url, Server.CHARSET);
            } catch (Throwable e) {
            }
        }

        return url;
    }
}
