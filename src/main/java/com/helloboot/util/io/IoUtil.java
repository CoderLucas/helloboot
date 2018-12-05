package com.helloboot.util.io;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lujianhao
 * @date 2018/12/5
 */
public class IoUtil {
    public static void writeToFile(byte[] bytes, File output) {
        if (bytes != null && bytes.length > 0 && output != null) {
            if (!output.getParentFile().exists()) {
                output.getParentFile().mkdirs();
            }

            FileOutputStream os = null;
            try {
                os = new FileOutputStream(output);
                os.write(bytes);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseUtil.closeQuiet(os);
            }
        }
    }
}
