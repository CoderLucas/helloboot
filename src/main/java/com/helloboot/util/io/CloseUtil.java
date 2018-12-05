package com.helloboot.util.io;

import java.io.Closeable;

/**
 * @author lujianhao
 * @date 2018/12/5
 */
public class CloseUtil {
    public static void closeQuiet(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
            }
        }
    }
}
