package com.helloboot.util.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;

import java.io.InputStream;
import java.util.Map;

/**
 * 请求配置类
 *
 * @author lujianhao
 * @date 2018/11/29
 */
public class HttpConfig {
    private HttpConfig() {
    }

    /**
     * 获取实例
     *
     * @return 返回当前对象
     */
    public static HttpConfig custom() {
        return new HttpConfig();
    }

    /**
     * HttpClient对象
     */
    private HttpClient client;

    /**
     * 请求方法
     */
    private HttpRequestBase method;

    /**
     * Header头信息
     */
    private Map<String, Object> headers;

    /**
     * 传递参数
     */
    private Map<String, Object> maps;

    /**
     * 输入编码
     */
    private String inenc = "UTF-8";

    /**
     * 输出编码
     */
    private String outenc = "UTF-8";

    /**
     * 解决多线程下载时，stream被close的问题
     */
    private static final ThreadLocal<InputStream> inputs = new ThreadLocal<>();

    /**
     * 解决多线程处理时，url被覆盖问题
     */
    private static final ThreadLocal<String> urls = new ThreadLocal<>();

    /**
     * @param client HttpClient对象
     * @return 返回当前对象
     */
    public HttpConfig client(HttpClient client) {
        this.client = client;
        return this;
    }

    /**
     * @param url 资源url
     * @return 返回当前对象
     */
    public HttpConfig url(String url) {
        urls.set(url);
        return this;
    }

    /**
     * @param headers Header头信息
     * @return 返回当前对象
     */
    public HttpConfig headers(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    /**
     * @param maps 传递参数
     * @return 返回当前对象
     */
    public HttpConfig maps(Map<String, Object> maps) {
        synchronized (getClass()) {
            if (this.maps == null || maps == null) {
                this.maps = maps;
            } else {
                this.maps.putAll(maps);
            }
        }
        return this;
    }

    /**
     * @param inenc 输入编码
     * @return 返回当前对象
     */
    public HttpConfig inenc(String inenc) {
        this.inenc = inenc;
        return this;
    }

    /**
     * @param outenc 输出编码
     * @return 返回当前对象
     */
    public HttpConfig outenc(String outenc) {
        this.outenc = outenc;
        return this;
    }

    /**
     * @param input 输出流对象
     * @return 返回当前对象
     */
    public HttpConfig input(InputStream input) {
        inputs.set(input);
        return this;
    }

    public HttpConfig method(HttpRequestBase method) {
        this.method = method;
        return this;
    }

    public HttpConfig get() {
        this.method = new HttpGet();
        return this;
    }

    public HttpConfig post() {
        this.method = new HttpPost();
        return this;
    }

    public HttpConfig put() {
        this.method = new HttpPut();
        return this;
    }

    public HttpConfig patch() {
        this.method = new HttpPatch();
        return this;
    }

    public HttpConfig delete() {
        this.method = new HttpDelete();
        return this;
    }

    public HttpRequestBase getMethod() {
        return method;
    }

    public HttpClient getClient() {
        return client;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public String getInenc() {
        return inenc;
    }

    public String getOutenc() {
        return outenc;
    }

    public InputStream getOuts() {
        return inputs.get();
    }

    public String getUrls() {
        return urls.get();
    }
}
