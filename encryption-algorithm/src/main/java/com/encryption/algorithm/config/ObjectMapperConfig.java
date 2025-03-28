package com.encryption.algorithm.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ObjectMapperConfig
 * @Description TODO
 * @date 2025/3/27 11:04
 **/
@Component
public class ObjectMapperConfig {

    /**
     * 获取默认配置
     * 是否允许使用注释：mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
     * 字段允许去除引号：mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
     * 允许单引号：mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
     * 允许转义字符：mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
     * 严格重复检测：mapper.configure(JsonParser.Feature.STRICT_DUPLICATE_DETECTION, true);
     * 时间字段输出时间戳：mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
     * 时间输出为毫秒而非纳秒：mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
     * 时间读取为毫秒而非纳秒：mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
     * mapper.setTimeZone(systemTimeZone);
     * mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
     * mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
     * @author haitao.hu
     * @date 2022/7/21 16:49
     * @param builder
     * @return
     */
    @Bean("defaultObjectMapper")
    @Primary
    public ObjectMapper getDefaultObjectMapper(Jackson2ObjectMapperBuilder builder){
        ObjectMapper mapper = builder.createXmlMapper(false).build();
        mapper.registerModule(new JavaTimeModule()).registerModule(new ParameterNamesModule()).registerModules(ObjectMapper.findModules());
        // 不检测失败字段映射
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 空对象不出错
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 不输出空值字段
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);

        return mapper;
    }
}