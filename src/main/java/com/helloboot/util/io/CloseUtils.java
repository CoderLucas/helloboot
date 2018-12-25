package com.helloboot.util.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author lujianhao
 * @date 2018/12/5
 */
public final class CloseUtils {
    private CloseUtils() {
        throw new UnsupportedOperationException("You can't instantiate CloseUtils...");
    }

    /**
     * Close the io stream.
     *
     * @param closeables closeables
     */
    public static void close(final Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Close the io stream quietly.
     *
     * @param closeables closeables
     */
    public static void closeQuietly(final Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ignored) {
                    // No output exception
                }
            }
        }
    }
}
