package com.demo.nacos.register;

import com.alibaba.cloud.nacos.registry.NacosAutoServiceRegistration;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.concurrent.CompletableFuture;

import static org.springframework.boot.actuate.health.Status.UP;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName NacosDelayRegisterRunner
 * @Description nacos优雅预热上线方案
 * @date 2025/3/14 14:22
 **/
@Component
@Slf4j
public class NacosDelayRegisterRunner implements ApplicationRunner {

    /**
     * 最大健康检查次数
     */
    private static final int CHECK_HEALTH_NACOS_REGISTER_MAX_TIMES = 10;

    @Resource
    private NacosAutoServiceRegistration nacosAutoServiceRegistration;

    @Resource
    private HealthEndpoint healthEndpoint;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 在这里编写应用程序启动后要执行的逻辑
        log.warn("---开始执行应用程序已启动，执行runner逻辑---");

        // 你还可以获取并处理命令行参数和应用程序参数
        handleCommandLineArguments(args);
    }

    /**
     * 读取程序启动参数并执行
     * @param args 启动参数
     */
    private void handleCommandLineArguments(ApplicationArguments args) {
        // 获取并处理命令行参数
        System.out.println("---命令行参数：---");
        for (String arg : args.getSourceArgs()) {
            System.out.println(arg);
        }

        // 获取并处理应用程序参数
        System.out.println("---应用程序参数：---");
        for (String name : args.getOptionNames()) {
            System.out.println(name + "=" + args.getOptionValues(name));
        }

        // 如果在启动参数手动设置了不注册nacos，就跳过手动注册，为了开发环境和backend
        if ( !checkDisableNacos(args.getSourceArgs()) ) {
            // 初次健康检查，预热
            this.firstHealthCheck();

            // 异步健康检查
            CompletableFuture.supplyAsync(() -> {
                log.warn("异步监测健康状态开始");

                Boolean isUp = false;
                // 等待5秒才注册
                try {
                    for (int i = 1; i <= CHECK_HEALTH_NACOS_REGISTER_MAX_TIMES; i++) {
                        isUp = this.isUpStatus();
                        log.warn("第{}次异步健康检测：{}", i, isUp);
                        if (isUp){
                            // 如果已启动，注册并中断循环
                            this.doNacosRegister();
                            break;
                        }
                        Thread.sleep(5000); // 模拟耗时操作
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return isUp;
            }).thenAccept(result -> {
                if (result) {
                    log.warn("异步监测健康状态结束");
                } else {
                    System.exit(99);
                    log.error("异步监测健康状态一直失败，请检查！");
                }
            });
        }
    }

    private boolean checkDisableNacos(String[] args){
        System.out.println(System.getProperty("spring.cloud.nacos.discovery.register-enabled"));
        for (String arg : args) {
            if (StringUtils.contains(arg, "spring.cloud.nacos.discovery.register-enabled") && StringUtils.contains(arg,"false")
                    || StringUtils.equals(System.getProperty("spring.cloud.nacos.discovery.register-enabled"), "false")){
                return true;
            }
        }
        return false;
    }

    /**
     * 进行nacos手动注册
     */
    private void doNacosRegister(){
        log.warn("nacos手动注册流程开始");
        try {
            // 临时获取权限拿参数
            // 通过反射拿registration属性。即使 registration 是私有字段（private），也可以通过反射获取。
            // 公共属性(public)才能get方法获取
            Field declaredField = nacosAutoServiceRegistration.getClass().getDeclaredField("registration");
            // 设置 registration 字段为可访问状态。
            // 说明：
            // 如果 registration 字段是私有的（private），默认情况下无法通过反射直接访问。
            // setAccessible(true) 可以绕过 Java 的访问控制检查，允许访问私有字段。
            // 这是一个危险操作，因为它破坏了封装性，应谨慎使用。
            declaredField.setAccessible(true);
            // 通过反射从 nacosAutoServiceRegistration 对象中获取 registration 字段的值。
            // 将该值强制转换为 NacosRegistration 类型。
            NacosRegistration nacosRegistration = (NacosRegistration) declaredField.get(nacosAutoServiceRegistration);
            // 将 registration 字段的访问权限恢复为原始状态（通常是不可访问状态）。
            // 说明：
            // 这是一个可选操作，目的是恢复字段的访问控制，避免对其他代码产生影响。
            // 在实际开发中，这一步通常可以省略，因为 setAccessible(true) 的作用范围仅限于当前反射操作。
            declaredField.setAccessible(false);

            // 如果开启了自动注册 那么就直接返回
            if (nacosRegistration.isRegisterEnabled()) {
                log.warn("nacos已打开自动注册，跳过手动注册！");
                return;
            }

            // 手动注册
            nacosRegistration.getNacosDiscoveryProperties().setRegisterEnabled(true);
            nacosAutoServiceRegistration.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            log.warn("nacos手动注册流程结束");
        }
    }

    /**
     * 进行初次健康检查
     */
    private void firstHealthCheck(){
        log.warn("开始进行初次预热健康检查");
        // 进行初次健康检查
        HealthComponent endpoint = healthEndpoint.health();
        log.warn("初次预热健康检查完成：" + endpoint.getStatus());
    }

    /**
     * 是否已启动
     * @return 是/否
     */
    private Boolean isUpStatus(){
        return UP.equals( healthEndpoint.health().getStatus() );
    }

}