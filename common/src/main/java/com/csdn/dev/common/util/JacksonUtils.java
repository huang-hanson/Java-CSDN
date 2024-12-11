package com.csdn.dev.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.*;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName JacksonUtils
 * @date 2024/12/11 14:34
 **/
@Slf4j
public class JacksonUtils {
    private static ObjectMapper mapper;

    private static ObjectMapper nonNullMapper;

    public static ObjectMapper getMapper() {
        if (mapper == null) {
            Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
            mapper = builder.createXmlMapper(false).build();
        }
        return mapper;
    }

    public static ObjectMapper getNonNullMapper() {
        if (nonNullMapper == null) {
            Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
            ObjectMapper build = builder.createXmlMapper(false).build();
            build.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            nonNullMapper = build;
        }
        return nonNullMapper;
    }

    /**
     * object转json字符串
     *
     * @param entity object
     * @return 转化后字符串
     */
    public static String jsonEncode(Object entity) {
        ObjectMapper mapper = getMapper();
        String str = null;
        try {
            str = mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            log.error("json encode fail {}-{}", e, entity);
        }
        return str;
    }

    public static String jsonEncodeWithNonNull(Object entity) {
        ObjectMapper mapper = getNonNullMapper();
        String str = null;
        try {
            str = mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            log.error("json encode fail {}-{}", e, entity);
        }
        return str;
    }

    /**
     * json decode
     *
     * @param jsonStr 待decode字符串
     * @return hashmap
     */
    public static HashMap<String, Object> jsonDecode(String jsonStr) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        ObjectMapper mapper = getMapper();
        HashMap<String, Object> tmpMap = null;
        try {
            tmpMap = mapper.readValue(jsonStr, new TypeReference<HashMap<String, Object>>() {
            });
        } catch (Exception e) {
            log.error("json decode fail {}-{}", e, jsonStr);
        }
        return tmpMap;
    }

    /**
     * json decode并找到指定key的值
     *
     * @param jsonStr 待decode字符串
     * @param keyName 待查找字段名
     * @return 查到的字段值
     */
    public static String jsonDecode(String jsonStr, String keyName) {
        if (org.apache.commons.lang3.StringUtils.isBlank(jsonStr)) {
            return null;
        }
        HashMap<String, Object> tmpMap = jsonDecode(jsonStr);
        String tmpString = null;
        if (tmpMap != null && tmpMap.get(keyName) != null) {
            tmpString = String.valueOf(tmpMap.get(keyName));
        }
        return tmpString;
    }

    public static <T> T jsonDecode(String jsonStr, Class<T> clazz) {
        if (org.apache.commons.lang3.StringUtils.isBlank(jsonStr)) {
            return null;
        }
        T result = null;
        try {
            result = getMapper().readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            log.error("json decode fail {}-{}", e, jsonStr);
        }
        return result;
    }

    public static <T> T jsonDecode(String jsonStr, TypeReference<T> typeReference) {
        if (org.apache.commons.lang3.StringUtils.isBlank(jsonStr)) {
            return null;
        }
        T result = null;
        try {
            result = getMapper().readValue(jsonStr, typeReference);
        } catch (JsonProcessingException e) {
            log.error("json decode fail {}-{}", e, jsonStr);
        }
        return result;
    }

    public static <T> List<T> jsonDecodeList(String jsonStr, Class<T> clazz) {
        return (List<T>) jsonDecodeCollection(jsonStr, clazz, List.class);
    }

    public static <T> Set<T> jsonDecodeSet(String jsonStr, Class<T> clazz) {
        return (Set<T>) jsonDecodeCollection(jsonStr, clazz, Set.class);
    }

    private static <T> Collection<T> jsonDecodeCollection(String jsonStr, Class<T> clazz, Class<? extends Collection> collectionClazz) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        ObjectMapper mapper = getMapper();
        Collection<T> result = new ArrayList<>();
        try {
            result = mapper.readValue(jsonStr, mapper.getTypeFactory().constructCollectionType(collectionClazz, clazz));
        } catch (JsonProcessingException e) {
            log.error("json decode fail {}-{}", e, jsonStr);
        }
        return result;
    }

}