package com.hanson.java;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import oshi.SystemInfo;
import oshi.software.os.OSSession;
import oshi.software.os.OperatingSystem;

import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName OShiTest
 * @Description oshi
 * @date 2025/2/20 13:33
 **/
@Slf4j
public class OShiTest {

    @Test
    void test_getOs() {
        // 创建 SystemInfo 对象
        SystemInfo systemInfo = new SystemInfo();

        // 获取操作系统信息
        OperatingSystem os = systemInfo.getOperatingSystem();

        // 获取当前登录会话信息
        List<OSSession> sessions = os.getSessions();

        // 遍历所有会话信息
        for (OSSession session : sessions) {
            log.warn("用户名: " + session.getUserName());
            log.warn("终端设备: " + session.getTerminalDevice());
            log.warn("登录时间: " + session.getLoginTime());
            log.warn("登录主机: " + session.getHost());
        }
    }

    @Test
    void test_str_length() {
        log.error("24oi6xq2aaakvagnqu7a".length()+"");
    }

}