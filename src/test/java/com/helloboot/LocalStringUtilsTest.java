package com.helloboot;

import com.helloboot.util.base.LocalStringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author lujianhao
 * @date 2019/1/24
 */
public class LocalStringUtilsTest {
    @Test
    public void isEmpty() {
        assertTrue(LocalStringUtils.isEmpty(""));
        assertTrue(LocalStringUtils.isEmpty(null));
        assertFalse(LocalStringUtils.isEmpty(" "));
    }

    @Test
    public void isTrimEmpty() {
        assertTrue(LocalStringUtils.isTrimEmpty(""));
        assertTrue(LocalStringUtils.isTrimEmpty(null));
        assertTrue(LocalStringUtils.isTrimEmpty(" "));
    }

    @Test
    public void isSpace() {
        assertTrue(LocalStringUtils.isSpace(""));
        assertTrue(LocalStringUtils.isSpace(null));
        assertTrue(LocalStringUtils.isSpace(" "));
        assertTrue(LocalStringUtils.isSpace("　 \n\t\r"));
    }

    @Test
    public void equals() {
        assertTrue(LocalStringUtils.equals(null, null));
        assertTrue(LocalStringUtils.equals("lucas", "lucas"));
        assertFalse(LocalStringUtils.equals("lucas", "Lucas"));
    }

    @Test
    public void equalsIgnoreCase() {
        assertTrue(LocalStringUtils.equalsIgnoreCase(null, null));
        assertFalse(LocalStringUtils.equalsIgnoreCase(null, "lucas"));
        assertTrue(LocalStringUtils.equalsIgnoreCase("lucas", "Lucas"));
        assertTrue(LocalStringUtils.equalsIgnoreCase("lucas", "lucas"));
        assertFalse(LocalStringUtils.equalsIgnoreCase("lucas", "lucass"));
    }

    @Test
    public void null2Length0() {
        assertEquals("", LocalStringUtils.null2Length0(null));
    }

    @Test
    public void length() {
        assertEquals(0, LocalStringUtils.length(null));
        assertEquals(0, LocalStringUtils.length(""));
        assertEquals(5, LocalStringUtils.length("lucas"));
    }

    @Test
    public void upperFirstLetter() {
        assertEquals("Lucas", LocalStringUtils.upperFirstLetter("lucas"));
        assertEquals("Lucas", LocalStringUtils.upperFirstLetter("Lucas"));
        assertEquals("1Lucas", LocalStringUtils.upperFirstLetter("1Lucas"));
    }

    @Test
    public void lowerFirstLetter() {
        assertEquals("lucas", LocalStringUtils.lowerFirstLetter("lucas"));
        assertEquals("lucas", LocalStringUtils.lowerFirstLetter("Lucas"));
        assertEquals("1lucas", LocalStringUtils.lowerFirstLetter("1lucas"));
    }

    @Test
    public void reverse() {
        assertEquals("sacul", LocalStringUtils.reverse("lucas"));
        assertEquals("文中试测", LocalStringUtils.reverse("测试中文"));
        assertEquals("", LocalStringUtils.reverse(null));
    }

    @Test
    public void toDBC() {
        assertEquals(" ,.&", LocalStringUtils.toDBC("　，．＆"));
    }

    @Test
    public void toSBC() {
        assertEquals("　，．＆", LocalStringUtils.toSBC(" ,.&"));
    }
}
