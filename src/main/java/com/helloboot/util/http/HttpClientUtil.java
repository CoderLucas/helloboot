package com.helloboot.util.http;


import com.helloboot.util.web.WebUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.util.*;

public final class HttpClientUtil {

    private static CloseableHttpClient clientHTTP;
    private static CloseableHttpClient clientHTTPS;

    private static final int CONNECTION_TIMEOUT = 3 * 1000;             // 请求超时时间 根据业务调整
    private static final int CONNECTION_REQUEST_TIMEOUT = 1 * 1000;     // 请求实例超时时间 根据业务调整
    private static final int SOCKET_RIMEOUT = 5 * 1000;                 // 等待数据超时时间 根据业务调整
    private static final int MAX_TOTAL = 500;                           // 连接池大小，默认20
    private static final int MAX_PERROUTE = 200;

    //传入参数特定类型
    private static final String ENTITY_STRING = "$ENTITY_STRING$";
    private static final String ENTITY_FILE = "$ENTITY_FILEE$";
    private static final String ENTITY_BYTES = "$ENTITY_BYTES$";
    private static final List<String> SPECIAL_ENTITIY = Arrays.asList(ENTITY_STRING, ENTITY_BYTES, ENTITY_FILE);

    static {
        clientHTTP = HCB.custom()
                .pool(MAX_TOTAL, MAX_PERROUTE)
                .timeout(CONNECTION_REQUEST_TIMEOUT, CONNECTION_TIMEOUT, SOCKET_RIMEOUT)
                .build();
        clientHTTPS = HCB.custom()
                .ssl()
                .pool(MAX_TOTAL, MAX_PERROUTE)
                .timeout(CONNECTION_REQUEST_TIMEOUT, CONNECTION_TIMEOUT, SOCKET_RIMEOUT)
                .build();
    }

    private static void create(HttpConfig config) {
        // 如果为空，设置默认client对象
        if (config.getClient() == null) {
            if (config.getUrls().toLowerCase().startsWith("https://")) {
                config.client(clientHTTPS);
            } else {
                config.client(clientHTTP);
            }
        }
    }

    /************************* 分割线 ***************************/
    /**
     * 以Get方式，请求资源或服务
     *
     * @param url 资源地址
     * @return 返回处理结果
     */

    public static String get(String url) {
        return send(HttpConfig.custom()
                .url(url)
                .get());
    }

    public static String get(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url + paramsToString(params))
                .get());
    }

    public static String get(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url + paramsToString(params))
                .headers(headers)
                .get());
    }

    public static String getByThrowable(String url) throws Exception {
        return sendByThrowable(HttpConfig.custom()
                .url(url)
                .get());
    }

//    public static File get2File(String url, File outFile) {
//        HttpConfig config = HttpConfig.custom()
//                .url(url)
//                .get();
//        try {
//            return fmt2File(executeByThrowable(config), outFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 以Post方式，请求资源或服务
     *
     * @param url    资源地址
     * @param params 请求参数
     * @return 返回处理结果
     */
    public static String post(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .post());
    }

    public static String post(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .post());
    }

    public static String post(String url, byte[] bytes) {
        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_BYTES, bytes);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .post());
    }

    public static String post(String url, byte[] bytes, Map<String, Object> headers) {
        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_BYTES, bytes);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .post());
    }

    public static String post(String url, String str) {
        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_STRING, str);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .post());
    }

    public static String post(String url, String str, Map<String, Object> headers) {
        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_STRING, str);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .post());
    }

    /**
     * 以Put方式，请求资源或服务
     *
     * @param url    资源地址
     * @param params 请求参数
     * @return 返回处理结果
     */
    public static String put(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .put());
    }

    public static String put(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .put());
    }

    /**
     * 以Delete方式，请求资源或服务
     *
     * @param url 资源地址
     * @return 返回处理结果
     */
    public static String delete(String url) {
        return send(HttpConfig.custom()
                .url(url)
                .delete());
    }

    public static String delete(String url, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .headers(headers)
                .delete());
    }

    /**
     * 以Patch方式，请求资源或服务
     *
     * @param url    资源地址
     * @param params 请求参数
     * @return 返回处理结果
     */
    public static String patch(String url, Map<String, Object> params) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .patch());
    }

    public static String patch(String url, Map<String, Object> params, Map<String, Object> headers) {
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .patch());
    }

    /**
     * 以Get方式，下载文件资源
     *
     * @param url
     * @return
     */
    public static InputStream down(String url) {
        HttpConfig config = HttpConfig.custom()
                .url(url)
                .get();
        try {
            return fmt2Stream(executeByThrowable(config), config.getOuts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream down(String url, Map<String, Object> params) {
        HttpConfig config = HttpConfig.custom()
                .url(url + paramsToString(params))
                .get();
        try {
            return fmt2Stream(executeByThrowable(config), config.getOuts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream down(String url, Map<String, Object> params, Map<String, Object> headers) {
        HttpConfig config = HttpConfig.custom()
                .url(url + paramsToString(params))
                .headers(headers)
                .get();
        try {
            return fmt2Stream(executeByThrowable(config), config.getOuts());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 以Post方式，上传文件资源
     *
     * @param url
     * @return
     */
    public static String upload(String url, String filePath) {
        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_FILE, filePath);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .post());
    }

    public static String upload(String url, Map<String, Object> headers, String filePath) {
        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_FILE, filePath);
        return send(HttpConfig.custom()
                .url(url)
                .maps(params)
                .headers(headers)
                .post());
    }

    /************************* 分割线 ***************************/

    /**
     * 请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     */
    public static String send(HttpConfig config) {
        try {
            return fmt2String(executeByThrowable(config));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sendByThrowable(HttpConfig config) throws Exception {
        return fmt2String(executeByThrowable(config));
    }

    /**
     * 打开连接
     *
     * @param
     * @return
     */
    private static HttpResponse executeByThrowable(HttpConfig config) throws Exception {
        create(config);
        HttpResponse response = null;

        // 创建请求对象
        HttpRequestBase request = config.getMethod();
        // 设置请求URL
        request.setURI(URI.create(config.getUrls()));
        // 添加headers
        Map<String, Object> headers = config.getHeaders();
        if (headers != null && headers.size() > 0) {
            for (String key : headers.keySet()) {
                request.addHeader(key, String.valueOf(headers.get(key)));
            }
        }
        request.addHeader("User-Agent", "Mozilla/5.0 (compatible; dxyask; +http://www.dxy.com/search.html)");

        if (HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
            List<NameValuePair> nvps = new ArrayList<>();
            //装填参数
            HttpEntity entity = map2HttpEntity(nvps, config.getMaps(), config.getInenc());
            //设置参数到请求对象中
            ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
        }

        response = config.getClient().execute(request);
        return response;
    }

    /**
     * 构建请求体
     *
     * @param nvps
     * @param map
     * @param inenc
     * @return
     */
    private static HttpEntity map2HttpEntity(List<NameValuePair> nvps, Map<String, Object> map, String inenc) {
        HttpEntity entity = null;
        if (map == null || map.size() == 0) {
            return null;
        }

        boolean isSpecial = false;
        // 拼接参数
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            //判断是否在之中
            if (SPECIAL_ENTITIY.contains(entry.getKey())) {
                isSpecial = true;
                if (ENTITY_STRING.contains(entry.getKey())) {
                    entity = new StringEntity(String.valueOf(entry.getValue()), inenc);
                    break;
                } else if (ENTITY_BYTES.equals(entry.getKey())) {
                    entity = new ByteArrayEntity((byte[]) entry.getValue());
                    break;
                } else if (ENTITY_FILE.equals(entry.getKey())) {
                    if (File.class.isAssignableFrom(entry.getValue().getClass())) {
                        entity = new FileEntity((File) entry.getValue(), ContentType.APPLICATION_OCTET_STREAM);
                    } else if (entry.getValue().getClass() == String.class) {
                        entity = new FileEntity(new File((String) entry.getValue()), ContentType.create("text/plain", "UTF-8"));
                    }
                    break;
                }
            } else {
                nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            try {
                if (!isSpecial) {
                    entity = new UrlEncodedFormEntity(nvps, inenc);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return entity;
    }


    /**
     * 构建请求体
     *
     * @param httpClient
     * @param params
     */
    public static void buildNameValuePairs(HttpEntityEnclosingRequestBase httpClient, Map<String, Object> params) {
        List<NameValuePair> nvps = new ArrayList<>();
        params.forEach((key, value) -> nvps.add(new BasicNameValuePair(key, WebUtil.encodeUrl(value.toString()))));

        try {
            httpClient.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将参数Map转为urlquery字符串
     *
     * @param params
     * @return
     */
    public static String paramsToString(Map<String, Object> params) {
        if (params == null || params.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder("?");
        params.forEach((key, value) -> sb.append(key)
                .append("=")
                .append(WebUtil.encodeUrl(value.toString()))
                .append("&"));
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 转化为字符串
     *
     * @param resp 响应对象
     * @return 返回处理结果
     */
    private static String fmt2String(HttpResponse resp) {
        String body = "";
        try {
            if (resp.getEntity() != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(resp.getEntity(), "UTF-8");
            } else {
                //有可能是head请求
                body = resp.getStatusLine().toString();
            }
            EntityUtils.consume(resp.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(resp);
        }
        return body;
    }

    /**
     * 转化为流
     *
     * @param resp  响应对象
     * @param input 输出流
     * @return 返回输出流
     */
    public static InputStream fmt2Stream(HttpResponse resp, InputStream input) {
        try {
            InputStream inputStream = resp.getEntity().getContent();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();

            input = new ByteArrayInputStream(baos.toByteArray());
            EntityUtils.consume(resp.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(resp);
            IOUtils.closeQuietly(input);
        }
        return input;
    }
//
//    public static File fmt2File(HttpResponse resp, File outFile) {
//        try {
//            byte[] byteArray = EntityUtils.toByteArray(resp.getEntity());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return outFile;
//    }

    /**
     * 尝试关闭response
     *
     * @param resp HttpResponse对象
     */
    private static void close(HttpResponse resp) {
        try {
            if (resp == null) {
                return;
            }
            //如果CloseableHttpResponse 是resp的父类，则支持关闭
            if (CloseableHttpResponse.class.isAssignableFrom(resp.getClass())) {
                ((CloseableHttpResponse) resp).close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToLocal(String destination, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }
}
