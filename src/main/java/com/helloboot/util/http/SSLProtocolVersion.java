package com.helloboot.util.http;

/**
 * The SSL protocol version
 *
 * @author lujianhao
 * @date 2018/11/29
 */
public enum SSLProtocolVersion {
    SSL("SSL"),
    SSLv3("SSLv3"),
    TLSv1("TLSv1"),
    TLSv1_1("TLSv1.1"),
    TLSv1_2("TLSv1.2");

    private String name;

    SSLProtocolVersion(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static SSLProtocolVersion find(String name) {
        for (SSLProtocolVersion pv : SSLProtocolVersion.values()) {
            if (pv.getName().toUpperCase().equals(name.toUpperCase())) {
                return pv;
            }
        }
        throw new RuntimeException("未支持当前ssl版本号：" + name);
    }
}
