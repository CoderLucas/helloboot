package com.helloboot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author lujianhao
 * @date 2018/12/10
 */
public class OKHttpDemo {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.baidu.com").build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
