package com.helloboot;

import com.helloboot.util.http.HttpClientUtil;
import com.helloboot.util.json.JsonUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lujianhao
 * @date 2019/7/12
 */
public class GeekBangTest {

    @Test
    public void login() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", "application/json, text/plain, */*");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        headers.put("Content-Type", "application/json");
        headers.put("Cookie","_ga=GA1.2.1185077377.1525689673; _gid=GA1.2.870281283.1562914559; GCID=a9706c0-1d6e453-9649f44-db54b84; SERVERID=1fa1f330efedec1559b3abbcb6e30f50|1562928206|1562927190");
        headers.put("Host", "account.geekbang.org");
        headers.put("Origin", "https://account.geekbang.org");
        headers.put("Referer", "https://account.geekbang.org/login");
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");

        Map<String, Object> param = new HashMap<>();
        param.put("appid", 1);
        param.put("captcha", "");
        param.put("country", 86);
        param.put("platform", 3);
        param.put("remember", 1);
        param.put("cellphone", "18358971066");
        param.put("password", "74.74.741");

      //  String returnStr =
        System.out.println(HttpClientUtil.post("https://account.geekbang.org/account/ticket/login", JsonUtil.beanToJson(param), headers));
    }

//    Cookie: _ga=GA1.2.1185077377.1525689673; _gid=GA1.2.870281283.1562914559; GCID=a9706c0-1d6e453-9649f44-db54b84; SERVERID=1fa1f330efedec1559b3abbcb6e30f50|1562928196|1562927190 | _gat=1;
//    Cookie:

}
