package com.hanson.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Sentinel
 * @Description TODO
 * @date 2024/7/26 18:14
 **/
@RestController
public class Sentinel {

    /**
     * 处理 /sentinel 的 GET 请求。
     * 该方法尝试进入一个 Sentinel 保护的资源。
     * 如果由于流量控制导致资源被阻止，则捕获 BlockException 并返回 "busy" 消息。
     * 否则，返回成功消息。
     *
     * @return 表示请求是否成功或被阻止的字符串。
     */
    @GetMapping("sentinel")
    public String sentinel() {
        // 声明一个Entry对象，用于后续的资源保护
        Entry entry = null;

        try {
            // 尝试进入资源保护，如果流量超过限制，会抛出BlockException
            SphU.entry("sentinel");
            // 如果没有抛出异常，则返回成功消息
            return "Hi, sentinel......!!!";
        } catch (BlockException e) {
            // 如果流量超过限制，捕获BlockException并打印堆栈信息，返回繁忙消息
            e.printStackTrace();
            return "busy......!!!";
        } finally {
            // 确保在最终块中退出Entry，释放资源
            if (entry != null) {
                entry.exit();
                // 这个return语句会覆盖上面的return，因此不会被执行到
                return "this is final";
            }
        }
    }

    /**
     * 初始化流量控制规则。
     * 在 Spring 容器初始化时执行，用于设置 Sentinel 的流量控制规则。
     * 这里将资源 "sentinel" 的 QPS 限制设置为 2。
     */
    @PostConstruct
    private static void initFlowRules() {
        // 创建一个FlowRule列表，用于存储流量控制规则
        List<FlowRule> rules = new ArrayList<>();

        // 创建一个新的FlowRule对象，设置流量控制的相关参数
        FlowRule rule = new FlowRule();
        // 设置受保护的资源名称为"sentinel"
        rule.setResource("sentinel");
        // 设置流量控制的方式为QPS（每秒查询率）
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置QPS的阈值为2，即每秒最多允许2次访问
        rule.setCount(2);

        // 将这个流量控制规则添加到规则列表中
        rules.add(rule);

        // 加载这些规则到FlowRuleManager中，使其生效
        FlowRuleManager.loadRules(rules);
    }
}