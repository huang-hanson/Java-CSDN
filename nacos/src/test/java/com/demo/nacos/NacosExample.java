package com.demo.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import org.junit.jupiter.api.Test;

import java.util.Properties;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName NacosExample
 * @Description TODO
 * @date 2025/3/10 14:53
 **/
public class NacosExample {

    @Test
    public void test_naming() throws NacosException {
        // 1.注册中心相关配置
        Properties properties = new Properties();
        properties.setProperty("serverAddr", "localhost:8848");
        properties.setProperty("namespace", "16eb09c7-20e9-449d-8454-7628391ec946");
        // 2.反射初始化NacosNamingService
        NamingService naming = NamingFactory.createNamingService(properties);
        // 2.服务注册
        naming.registerInstance("nacos-demo-v1-config", "127.0.0.1", 8080, "CSDN_DEV_BOOT_GROUP");
    }

}