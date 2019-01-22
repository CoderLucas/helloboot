package com.helloboot;

import com.helloboot.util.convert.ConvertUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * @author lujianhao
 * @date 2019/1/22
 */
public class ConvertUtilsTest {
    private String mBits = "0000000000001000110110110011001101000101101010110000001000100011";
    private byte[] mBytes    = new byte[]{0x00, 0x08, (byte) 0xdb, 0x33, 0x45, (byte) 0xab, 0x02, 0x23};
    private String hexString = "0008DB3345AB0223";

    private char[] mChars1 = new char[]{'0', '1', '2'};
    private byte[] mBytes1 = new byte[]{48, 49, 50};

    @Test
    public void bytesAndBits() {
        assertTrue(
                Arrays.equals(
                        mBytes,
                        ConvertUtils.bits2Bytes(mBits))
        );
        assertEquals(
                mBits,
                ConvertUtils.bytes2Bits(mBytes)
        );
    }

    @Test
    public void bytesAndChars() {
        assertTrue(
                Arrays.equals(
                        mBytes1,
                        ConvertUtils.chars2Bytes(mChars1)
                )
        );
        assertTrue(
                Arrays.equals(
                        mChars1,
                        ConvertUtils.bytes2Chars(mBytes1)
                )
        );
    }

    @Test
    public void bytes2HexString() {
        assertTrue(
                Arrays.equals(
                        mBytes,
                        ConvertUtils.hexString2Bytes(hexString)
                )
        );
        assertEquals(
                hexString,
                ConvertUtils.bytes2HexString(mBytes)
        );
    }

    @Test
    public void inputStream2Bytes_bytes2InputStream() throws Exception {
        String string = "this is test string";
        assertTrue(
                Arrays.equals(
                        string.getBytes("UTF-8"),
                        ConvertUtils.inputStream2Bytes(ConvertUtils.bytes2InputStream(string.getBytes("UTF-8")))
                )
        );
    }

    @Test
    public void inputStream2String_string2InputStream() {
        String string = "this is test string";
        assertEquals(
                string,
                ConvertUtils.inputStream2String(ConvertUtils.string2InputStream(string, "UTF-8"), "UTF-8")
        );
    }}
