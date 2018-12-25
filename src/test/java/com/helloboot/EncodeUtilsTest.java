package com.helloboot;

import com.helloboot.util.encode.EncodeUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author lujianhao
 * @date 2018/12/21
 */
public class EncodeUtilsTest {

    @Test
    public void urlEncode_urlDecode() {
        String urlEncodeString = "%E5%93%88%E5%93%88%E5%93%88";
        assertEquals(urlEncodeString, EncodeUtils.urlEncode("哈哈哈"));
        assertEquals(urlEncodeString, EncodeUtils.urlEncode("哈哈哈", "UTF-8"));

        assertEquals("哈哈哈", EncodeUtils.urlDecode(urlEncodeString));
        assertEquals("哈哈哈", EncodeUtils.urlDecode(urlEncodeString, "UTF-8"));
    }

    @Test
    public void base64Decode_base64Encode() {
        assertTrue(
                Arrays.equals(
                        "lucas".getBytes(),
                        EncodeUtils.base64Decode(EncodeUtils.base64Encode("lucas"))
                )
        );
        assertTrue(
                Arrays.equals(
                        "lucas".getBytes(),
                        EncodeUtils.base64Decode(EncodeUtils.base64Encode("lucas".getBytes()))
                )
        );
        assertEquals(
                "bHVjYXM=",
                EncodeUtils.base64Encode2String("lucas")
        );
        assertEquals(
                "bHVjYXM=",
                EncodeUtils.base64Encode2String("lucas".getBytes())
        );
        assertTrue(
                Arrays.equals(
                        "lucas".getBytes(),
                        EncodeUtils.base64Decode(EncodeUtils.base64Encode2String("lucas".getBytes()))
                )
        );
        assertTrue(
                Arrays.equals(
                        "bHVjYXM=".getBytes(),
                        EncodeUtils.base64Encode("lucas".getBytes())
                )
        );
    }

    @Test
    public void htmlEncode_htmlDecode() {
        String html = "<html>" +
                "<head>" +
                "<title>我的第一个 HTML 页面</title>" +
                "</head>" +
                "<body>" +
                "<p>body 元素的内容会显示在浏览器中。</p>" +
                "<p>title 元素的内容会显示在浏览器的标题栏中。</p>" +
                "</body>" +
                "</html>";
        String encodeHtml = "&lt;html&gt;&lt;head&gt;&lt;title&gt;我的第一个 HTML 页面&lt;/title&gt;&lt;/head&gt;&lt;body&gt;&lt;p&gt;body 元素的内容会显示在浏览器中。&lt;/p&gt;&lt;p&gt;title 元素的内容会显示在浏览器的标题栏中。&lt;/p&gt;&lt;/body&gt;&lt;/html&gt;";

        assertEquals(encodeHtml, EncodeUtils.htmlEncode(html));

        assertEquals(html, EncodeUtils.htmlDecode(encodeHtml).toString());
    }

    @Test
    public void binEncode_binDecode() {
        String test = "test";
        String binary = EncodeUtils.binEncode(test);
        assertEquals("test", EncodeUtils.binDecode(binary));
    }
}
