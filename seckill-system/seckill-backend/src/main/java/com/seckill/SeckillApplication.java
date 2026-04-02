package com.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 秒杀系统启动类
 */
@SpringBootApplication
@MapperScan("com.seckill.mapper")
@EnableAspectJAutoProxy
public class SeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
        System.out.println("=====================================");
        System.out.println("    商城秒杀系统启动成功！");
        System.out.println("=====================================");
    }
}
