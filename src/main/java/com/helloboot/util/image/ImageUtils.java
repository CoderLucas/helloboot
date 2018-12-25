package com.helloboot.util.image;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author lujianhao
 * @date 2018/12/17
 */
public class ImageUtils {
    private ImageUtils() {
        throw new UnsupportedOperationException("You can't instantiate ImageUtils...");
    }

    /**
     * Return the compressed image using scale.
     * (Nothing happens when the scaling is less than or equal to 0)
     *
     * @param originalImage the source of image.
     * @param widthScale    the width scaling.
     * @param heightScale   the height scaling.
     * @return the compressed image.
     */
    public static BufferedImage compressByScale(BufferedImage originalImage, Integer widthScale, Integer heightScale) {
        if (widthScale <= 0) {
            widthScale = 1;
        }
        if (heightScale <= 0) {
            heightScale = 1;
        }
        int width = originalImage.getWidth() * widthScale;
        int height = originalImage.getHeight() * heightScale;
        BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }

    /**
     * compress image.
     * (Nothing happens when the scaling is less than or equal to 0)
     *
     * @param srcImagePath the path of read image.
     * @param toImagePath  the path of write iamge.
     * @param widthScale   the width ratio.
     * @param heightScale  the height ratio.
     */
    public static void compressByScale(String srcImagePath, String toImagePath, int widthScale, int heightScale) {
        File file = new File(srcImagePath);
        enlargement(toImagePath, widthScale, heightScale, file);
    }

    /**
     * compress image in equal proportion.
     * (Nothing happens when the scaling is less than or equal to 0)
     *
     * @param srcImagePath the read imagePath.
     * @param toImagePath  the write iamgePath.
     * @param scale   the scaling.
     */
    public static void compressByScale(String srcImagePath, String toImagePath, int scale) {
        File file = new File(srcImagePath);
        enlargement(toImagePath, scale, scale, file);
    }

    /**
     * Return whether it is a image according to the file name.
     *
     * @param file The file.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isImage(final File file) {
        return file != null && isImage(file.getPath());
    }

    /**
     * Return whether it is a image according to the file name.
     *
     * @param filePath The path of file.
     * @return {@code true}: yes
     * {@code false}: no
     */
    public static boolean isImage(final String filePath) {
        String path = filePath.toUpperCase();
        return path.endsWith(".PNG") || path.endsWith(".JPG")
                || path.endsWith(".JPEG") || path.endsWith(".BMP")
                || path.endsWith(".GIF") || path.endsWith(".WEBP");
    }

    /**
     * Return the type of image.
     *
     * @param filePath The path of file.
     * @return the type of image
     */
    public static String getImageType(final String filePath) {
        return getImageType(getFileByPath(filePath));
    }

    /**
     * Return the type of image.
     *
     * @param file The file.
     * @return the type of image
     */
    public static String getImageType(final File file) {
        if (file == null) {
            return "";
        }
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            String type = getImageType(is);
            if (type != null) {
                return type;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getFileExtension(file.getAbsolutePath()).toUpperCase();
    }

    private static String getFileExtension(final String filePath) {
        if (isSpace(filePath)) {
            return filePath;
        }
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi) {
            return "";
        }
        return filePath.substring(lastPoi + 1);
    }

    private static String getImageType(final InputStream is) {
        if (is == null) {
            return null;
        }
        try {
            byte[] bytes = new byte[8];
            return is.read(bytes, 0, 8) != -1 ? getImageType(bytes) : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getImageType(final byte[] bytes) {
        if (isJPEG(bytes)) {
            return "JPEG";
        }
        if (isGIF(bytes)) {
            return "GIF";
        }
        if (isPNG(bytes)) {
            return "PNG";
        }
        if (isBMP(bytes)) {
            return "BMP";
        }
        return null;
    }

    private static boolean isJPEG(final byte[] b) {
        return b.length >= 2
                && (b[0] == (byte) 0xFF) && (b[1] == (byte) 0xD8);
    }

    private static boolean isGIF(final byte[] b) {
        return b.length >= 6
                && b[0] == 'G' && b[1] == 'I'
                && b[2] == 'F' && b[3] == '8'
                && (b[4] == '7' || b[4] == '9') && b[5] == 'a';
    }

    private static boolean isPNG(final byte[] b) {
        return b.length >= 8
                && (b[0] == (byte) 137 && b[1] == (byte) 80
                && b[2] == (byte) 78 && b[3] == (byte) 71
                && b[4] == (byte) 13 && b[5] == (byte) 10
                && b[6] == (byte) 26 && b[7] == (byte) 10);
    }

    private static boolean isBMP(final byte[] b) {
        return b.length >= 2
                && (b[0] == 0x42) && (b[1] == 0x4d);
    }

    ///////////////////////////////////////////////////////////////////////////
    // other utils methods
    ///////////////////////////////////////////////////////////////////////////

    private static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    private static boolean isSpace(final String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static void enlargement(String toImagePath, int widthRatio, int heightRatio, File file) {
        try (FileOutputStream out = new FileOutputStream(toImagePath)) {
            //读入文件
            String prefix = getFileSuffix(file);
            // 构造Image对象
            BufferedImage srcBuffer = ImageIO.read(file);
            // 按比例缩减图像
            BufferedImage imageBuffer = ImageUtils.compressByScale(srcBuffer, widthRatio, heightRatio);
            ImageIO.write(imageBuffer, prefix, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getFileSuffix(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.indexOf(".") + 1);
    }
}
