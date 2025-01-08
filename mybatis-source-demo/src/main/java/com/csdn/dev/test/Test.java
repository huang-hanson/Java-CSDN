package com.csdn.dev.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Test
 * @Description TODO
 * @date 2025/1/3 17:30
 **/
public class Test {
    public static void main(String[] args) throws IOException {
        //1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");

        //2.解析了配置文件，并创建了sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        System.out.println(sqlSessionFactory);
    }
}