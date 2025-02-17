package com.db.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MongoConfig
 * @Description mongo配置
 * @date 2025/2/17 17:45
 **/
@Configuration
public class MongoConfig {
    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        String connectionString = "mongodb://127.0.0.1:27017/csdn";
        return new SimpleMongoClientDatabaseFactory(connectionString);
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }
}