package com.csdn.dev.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.Reader;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MyBatisConfig
 * @date 2024/12/31 17:51
 **/
@Slf4j
public class MyBatisConfig {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            log.error("初始化SqlSessionFactory成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}