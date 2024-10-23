package com.hanson.eventlogging;

//import com.alibaba.cloud.nacos.discovery.NacosDiscoveryAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName EventLoggingApplication
 * @Description TODO
 * @date 2024/7/30 10:03
 **/
@SpringBootApplication
public class EventLoggingApplication {

    public static void main(String[] args) {
        System.out.println("event-logging-demo成功启动(oﾟvﾟ)ノ！！！");
        SpringApplication.run(EventLoggingApplication.class, args);
    }

}