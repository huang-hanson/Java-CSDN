package com.hanson.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName DashBoard
 * @Description TODO
 * @date 2024/7/26 18:56
 **/
@RestController
public class DashBoard {

    /**
     * 处理对 "/sentinel" 路径的GET请求。
     * 此方法返回一个包含Sentinel相关信息的字符串。
     * 当Sentinel流量控制触发时，将调用blockHandler方法。
     *
     * @return 包含Sentinel信息的字符串。
     */
    @GetMapping("dashboard")
    @SentinelResource(value = "sentinel", blockHandler = "helloBlockHandler")
    public String hello() {
        return String.format("Hello sentinel............");
    }

    /**
     * Sentinel流量控制触发的回退处理方法。
     * 当Sentinel流量控制规则阻止了对原始方法的访问时，将调用此方法。
     *
     * @param e 触发流量控制的异常信息。
     * @return 一个回退响应字符串。
     */
    public String helloBlockHandler(BlockException e){
        e.printStackTrace();
        return "this is helloBlockHandler.....";
    }
}