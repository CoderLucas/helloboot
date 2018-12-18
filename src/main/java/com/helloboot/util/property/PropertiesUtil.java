package com.helloboot.util.property;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author lujianhao
 * @date 2018/12/17
 */
public class PropertiesUtil {

    /**
     * 从系统属性文件中获取相应的值.
     *
     * @param key key
     * @return 返回value
     */
    public static String key(String key) {
        return System.getProperty(key);
    }

    /**
     * 根据Key读取Value.
     *
     * @param filePath 属性文件
     * @param key      需要读取的属性
     */
    public static String getValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        try (InputStream in = new BufferedInputStream(new FileInputStream(filePath))) {
            pps.load(in);
            return pps.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取Properties的全部信息.
     *
     * @param filePath 读取的属性文件
     * @return 返回所有的属性 key:value<>key:value
     */
    public static Map<String, String> getAllProperties(String filePath) throws IOException {
        Map<String, String> map = new HashMap<>(0);
        try (InputStream in = new BufferedInputStream(new FileInputStream(filePath))) {
            return properties(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static Map<String, String> properties(InputStream in) {
        Map<String, String> map = new HashMap<>(5);
        Properties pps = new Properties();
        try {
            pps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration en = pps.propertyNames();
        while (en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            map.put(strKey, strValue);
        }
        return map;
    }
}
