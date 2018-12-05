package com.helloboot.util.http;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * HttpClient创建者
 *
 * @author lujianhao
 * @date 2018/11/29
 */
public class HCB extends HttpClientBuilder {

    /**
     * 默认ssl协议版本为 SSL
     */
    private SSLProtocolVersion sslpv = SSLProtocolVersion.SSL;

    private HCB() {
    }

    public static HCB custom() {
        return new HCB();
    }

    /**
     * 设置超时时间
     *
     * @param connectionRequestTime 请求实例超时时间，单位-毫秒
     * @param connectTime           请求超时时间，单位-毫秒
     * @param socketTime            等待数据超时时间，单位-毫秒
     * @return 返回当前对象
     */
    public HCB timeout(int connectionRequestTime, int connectTime, int socketTime) {
        // 配置请求的超时设置
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTime)
                .setConnectTimeout(connectTime)
                .setSocketTimeout(socketTime)
                .build();
        return (HCB) this.setDefaultRequestConfig(config);
    }

    public HCB timeout(int time) {
        return this.timeout(time, time, time);
    }

    /**
     * 设置ssl安全链接，默认ssl版本SSLv3
     *
     * @return 返回当前对象
     */
    public HCB ssl() {
        return (HCB) this.setSSLSocketFactory(getSSLCSF(sslpv));
    }

    /**
     * sslpv 设置ssl安全链接
     *
     * @return 返回当前对象
     */
    public HCB ssl(SSLProtocolVersion sslpv) {
        this.sslpv = sslpv;
        return (HCB) this.setSSLSocketFactory(getSSLCSF(this.sslpv));
    }

    /**
     * sslpv 设置ssl安全链接
     *
     * @return 返回当前对象
     */
    public HCB ssl(String sslpv) {
        this.sslpv = SSLProtocolVersion.find(sslpv);
        return (HCB) this.setSSLSocketFactory(getSSLCSF(this.sslpv));
    }

    /**
     * 设置连接池（默认开启https）
     *
     * @param maxTotal           最大连接数
     * @param defaultMaxPerRoute 每个路由默认连接数
     * @return 返回当前对象
     */
    public HCB pool(int maxTotal, int defaultMaxPerRoute) {
        //设置连接池大小
        PoolingHttpClientConnectionManager connectionManager = getPHCCM();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return (HCB) this.setConnectionManager(connectionManager);
    }

    /**
     * 设置代理
     *
     * @param hostOrIP 代理host或者ip
     * @param port     代理端口
     * @return 返回当前对象
     */
    public HCB proxy(String hostOrIP, int port) {
        // 依次是代理地址，代理端口号，协议类型
        HttpHost proxy = new HttpHost(hostOrIP, port, "http");
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        return (HCB) this.setRoutePlanner(routePlanner);
    }

    /**
     * 重试（如果请求是幂等的，就再次尝试）
     *
     * @param tryTimes 重试次数
     * @return 返回当前对象
     */
    public HCB retry(final int tryTimes) {
        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= tryTimes) {
                    // 如果已经重试了n次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {
                    // 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {
                    // 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    // 超时
                    return true;
                }
                if (exception instanceof UnknownHostException) {
                    // 目标服务器不可达
                    return true;
                }
                if (exception instanceof ConnectTimeoutException) {
                    // 连接被拒绝
                    return true;
                }
                if (exception instanceof SSLException) {
                    // SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
        this.setRetryHandler(httpRequestRetryHandler);
        return this;
    }

    private PoolingHttpClientConnectionManager getPHCCM() {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", getSSLCSF(this.sslpv))
                .build();
        return new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    }

    /**
     * 绕过证书验证，信任所有主机端口，重写X509TrustManager接口
     *
     * @param sslpv
     * @return
     */
    private SSLConnectionSocketFactory getSSLCSF(SSLProtocolVersion sslpv) {
        // 绕过验证
        SSLContext sslContext = createIgnoreVerifySSL(sslpv);
        HostnameVerifier verifier = (hostname, session) -> true;
        return new SSLConnectionSocketFactory(sslContext, verifier);
    }

    /**
     * 绕过证书验证，重写X509TrustManager接口
     *
     * @param sslpv
     * @return
     */
    private static SSLContext createIgnoreVerifySSL(SSLProtocolVersion sslpv) {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance(sslpv.getName());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        try {
            sslContext.init(null, new TrustManager[]{trustManager}, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslContext;
    }
}
