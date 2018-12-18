package com.helloboot.util.encrypt;

import com.alibaba.druid.sql.visitor.functions.Hex;
import com.helloboot.util.system.Server;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author lujianhao
 * @date 2018/12/5
 */
public class AESUtil {
    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 初始化密钥
     *
     * @return byte[] 密钥
     * @throws Exception
     */
    private static byte[] initSecretKey() {
        //返回生成指定算法的秘密密钥的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        //初始化此密钥生成器，使其具有确定的密钥大小
        //AES 要求密钥长度为 128
        kg.init(128);
        //生成一个密钥
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    public static SecretKeySpec generatKey(String key, int keyLength) throws UnsupportedEncodingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(Server.CHARSET), KEY_ALGORITHM);
        return secretKeySpec;
    }

    /**
     * 转换密钥
     *
     * @param key 二进制密钥
     * @return 密钥
     */
    private static Key toKey(byte[] key) {
        //生成密钥
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[]    加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  二进制密钥
     * @return byte[]    加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 加密
     *
     * @param data            待加密数据
     * @param key             二进制密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[]    加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        //还原密钥
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }

    /**
     * 加密
     *
     * @param data            待加密数据
     * @param key             密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[]    加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为加密模式
        if (cipherAlgorithm.contains("ECB")) {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } else if (cipherAlgorithm.contains("CBC")) {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(Arrays.copyOf(key.getEncoded(), 16)));
        }

        //执行操作
        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  二进制密钥
     * @return byte[]    解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[]    解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     *
     * @param data            待解密数据
     * @param key             二进制密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[]    解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        //还原密钥
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }

    /**
     * 解密
     *
     * @param data            待解密数据
     * @param key             密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[]    解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为解密模式
        if (cipherAlgorithm.contains("ECB")) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else if (cipherAlgorithm.contains("CBC")) {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(Arrays.copyOf(key.getEncoded(), 16)));
        }
        //执行操作
        return cipher.doFinal(data);
    }

    /**
     * AES/ECB/PKCS5Padding解密
     * @param content
     * @return
     * @throws Exception
     */
    public static String decrypt(String content,Key key) {
        String decodeText = null;
        try {
            //.base64解码
            byte[] baseDecode = new BASE64Decoder().decodeBuffer(content);
            //.创建密码器(AES算法下PKCS5Padding和PKCS7Padding没区别)
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(baseDecode);// AES解密
            decodeText = new String(result, Server.CHARSET);
        } catch (Throwable e){
            e.printStackTrace();
        }
        return decodeText;
    }

    /**
     * AES/ECB/PKCS5Padding加密
     * @param content
     * @return
     * @throws Exception
     */
    public static String encrypt(String content,Key key){
        String encodeText = null;
        try {
            //.创建密码器(AES算法下PKCS5Padding和PKCS7Padding没区别)
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes(Server.CHARSET);
            cipher.init(Cipher.ENCRYPT_MODE, key);// AES加密
            byte[] result = cipher.doFinal(byteContent);
            encodeText = new BASE64Encoder().encode(result);//Base64加密
        } catch (Throwable e){
            e.printStackTrace();
        }
        return encodeText;
    }

    private static String showByteArray(byte[] data) {
        if (null == data) {
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for (byte b : data) {
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        byte[] key = initSecretKey();
        System.out.println("key：" + showByteArray(key));

        Key k = toKey(key);

        long temp1total = 0l;
        long temp2total = 0l;
        long temp3total = 0l;

        for (int i = 0; i < 100; i++) {
            long a = System.currentTimeMillis();

            String data = "AES数据dfjsljflksjfklsjfjlisjflksjlkfjslkjflwjpoiepop&(&(&(d但是家居服 i 设计福利社福建省科技分开了世界疯狂了斯捷克洛夫救死扶伤家";
            System.out.println("加密前数据: string:" + data);
            System.out.println("加密前数据: byte[]:" + showByteArray(data.getBytes()));
            System.out.println();
            byte[] encryptData = encrypt(data.getBytes(), k);

            long b = System.currentTimeMillis();

            System.out.println("加密后数据: byte[]:" + showByteArray(encryptData));
            System.out.println();

            long c = System.currentTimeMillis();

            byte[] decryptData = decrypt(encryptData, k);
            System.out.println("解密后数据: byte[]:" + showByteArray(decryptData));
            System.out.println("解密后数据: string:" + new String(decryptData));

            long d = System.currentTimeMillis();

            long temp1 = b - a;
            long temp2 = c - b;
            long temp3 = d - a;

            temp1total += temp1;
            temp2total += temp2;
            temp3total += temp3;

            System.out.println("加密时间:" + temp1);
            System.out.println("解密时间:" + temp2);
            System.out.println("费时:" + temp3);
        }

        System.out.println("平均加密时间:" + temp1total / 100);
        System.out.println("平均解密时间:" + temp2total / 100);
        System.out.println("平均费时:" + temp3total / 100);

    }
}
