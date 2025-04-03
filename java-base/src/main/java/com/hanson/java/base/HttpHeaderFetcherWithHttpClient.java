package com.hanson.java.base;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.Header;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName HttpHeaderFetcherWithHttpClient
 * @Description 打印请求头
 * @date 2025/3/31 14:41
 **/
public class HttpHeaderFetcherWithHttpClient {

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 定义目标 URL
            String urlString = "http://tper.51job.com/mkt-media/open/noauth/ocpx/standard/get-click-data" +
                    "?account_id=__ACCOUNT_ID__" +
                    "&click_id=__CLICK_ID__" +
                    "&click_time=__CLICK_TIME__" +
                    "&callback=__CALLBACK__" +
                    "&impression_id=__IMPRESSION_ID__" +
                    "&request_id=__REQUEST_ID__" +
                    "&channel=0ed5103f88f9b3417c8cba24b8cc268d" +
                    "&channel2=0ed5103f88f9b3417c8cba24b8cc268d";

            // 创建 HEAD 请求
            HttpHead request = new HttpHead(urlString);

            // 执行请求
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                System.out.println("请求时间："+ System.currentTimeMillis());
                // 打印响应状态码
                System.out.println("HTTP Response Code: " + response.getStatusLine().getStatusCode());

                // 打印所有响应头
                System.out.println("Response Headers:");
                for (Header header : response.getAllHeaders()) {
                    System.out.println(header.getName() + ": " + header.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}