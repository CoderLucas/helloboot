package com.helloboot;

import com.helloboot.util.base.CharacterUtils;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author lujianhao
 * @date 2019/1/28
 */
public class CharacterUtilsTest {
    @Test
    public void isEmoji() {
        assertTrue(CharacterUtils.isEmoji("👌".charAt(0)));
        assertFalse(CharacterUtils.isEmoji("哈".charAt(0)));
        assertFalse(CharacterUtils.isEmoji("a".charAt(0)));
        assertFalse(CharacterUtils.isEmoji("1".charAt(0)));
        assertFalse(CharacterUtils.isEmoji("&".charAt(0)));

        assertTrue(!CharacterUtils.isNotEmoji("👌".charAt(0)));
        assertFalse(!CharacterUtils.isNotEmoji("哈".charAt(0)));
        assertFalse(!CharacterUtils.isNotEmoji("a".charAt(0)));
        assertFalse(!CharacterUtils.isNotEmoji("1".charAt(0)));
        assertFalse(!CharacterUtils.isNotEmoji("&".charAt(0)));
    }

    @Test
    public void containEmoji() {
        assertTrue(CharacterUtils.containEmoji("👌Lucas测试~"));
        assertFalse(CharacterUtils.containEmoji("Lucas测试~"));
    }

    @Test
    public void cleanEmoji() {
        assertTrue(CharacterUtils.containEmoji("👌Lucas测试~"));
        assertFalse(CharacterUtils.containEmoji(
                CharacterUtils.cleanEmoji("👌Lucas测试~")
        ));
    }
}
