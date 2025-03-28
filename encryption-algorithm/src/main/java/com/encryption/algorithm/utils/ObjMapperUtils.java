package com.encryption.algorithm.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ObjMapperUtils
 * @Description TODO
 * @date 2025/3/27 11:04
 **/
@Component
@Slf4j
public class ObjMapperUtils {
    @Resource(name="defaultObjectMapper")
    private ObjectMapper objectMapper;

    public <T> T parseObject(String json, Class<T> clazz){
        if(StringUtils.isBlank(json)){
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("jackson 转换 json失败，异常：{}", e.getMessage());
            return null;
        }
    }

    public <T> T parseObject(String json, TypeReference<T> typeReference){
        if(StringUtils.isBlank(json)){
            return null;
        }
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            log.error("jackson 转换 json失败，异常：{}", e.getMessage());
            return null;
        }
    }

    public String toJSONString(Object obj){
        if(obj==null){
            return StringUtils.EMPTY;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("对象 转换 字符串失败，异常：{}", e.getMessage());
            return "";
        }
    }
}
