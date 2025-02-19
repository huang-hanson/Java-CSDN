package com.db.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestRedis
 * @Description TestRedis
 * @date 2025/2/18 16:25
 **/
@Slf4j
@SpringBootTest
public class TestRedis {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试设置单个键值对
     */
    @Test
    void testSetValue() {
        stringRedisTemplate.opsForValue().set("testKey", "testValue");
        String value = stringRedisTemplate.opsForValue().get("testKey");
        log.info("设置并获取单个键值对，值为: {}", value);
    }

    /**
     * 测试设置带有过期时间的键值对  10秒过期
     */
    @Test
    void testSetValueWithExpiration() {
        stringRedisTemplate.opsForValue().set("expiringKey", "expiringValue", 10, TimeUnit.SECONDS);
        String value = stringRedisTemplate.opsForValue().get("expiringKey");
        log.info("设置带有过期时间的键值对，值为: {}", value);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        value = stringRedisTemplate.opsForValue().get("expiringKey");
        log.info("过期时间已到，键值对已过期，值为: {}", value);
    }

    /**
     * 测试获取单个键的值
     */
    @Test
    void testGetValue() {
        stringRedisTemplate.opsForValue().set("existingKey", "existingValue");
        String value = stringRedisTemplate.opsForValue().get("existingKey");
        log.info("获取单个键的值，值为: {}", value);
    }

    /**
     * 测试删除单个键
     */
    @Test
    void testDeleteKey() {
        stringRedisTemplate.opsForValue().set("toDeleteKey", "toDeleteValue");
        Boolean result = stringRedisTemplate.delete("toDeleteKey");
        log.info("删除单个键，结果: {}", result);
    }

    /**
     * 测试批量删除键
     */
    @Test
    void testDeleteKeys() {
        stringRedisTemplate.opsForValue().set("key1", "value1");
        stringRedisTemplate.opsForValue().set("key2", "value2");
        Long deletedCount = stringRedisTemplate.delete(Arrays.asList("key1", "key2"));
        log.info("批量删除键，删除数量: {}", deletedCount);
    }

    /**
     * 测试设置哈希表
     */
    @Test
    void testSetHash() {
        Map<String, String> hash = new HashMap<>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");
        stringRedisTemplate.opsForHash().putAll("testHash", hash);
        Map<Object, Object> result = stringRedisTemplate.opsForHash().entries("testHash");
        log.info("设置哈希表，结果: {}", result);
    }

    /**
     * 测试获取哈希表中的单个字段值
     */
    @Test
    void testGetHashField() {
        stringRedisTemplate.opsForHash().put("testHash", "field1", "value1");
        Object value = stringRedisTemplate.opsForHash().get("testHash", "field1");
        log.info("获取哈希表中的单个字段值，值为: {}", value);
    }

    /**
     * 测试获取哈希表的所有字段和值
     */
    @Test
    void testGetAllHashFields() {
        Map<String, String> hash = new HashMap<>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");
        stringRedisTemplate.opsForHash().putAll("testHash", hash);
        Map<Object, Object> result = stringRedisTemplate.opsForHash().entries("testHash");
        log.info("获取哈希表的所有字段和值，结果: {}", result);
    }

    /**
     * 测试向列表左侧插入元素
     */
    @Test
    void testLeftPushToList() {
        stringRedisTemplate.opsForList().leftPush("testList", "element1");
        stringRedisTemplate.opsForList().leftPush("testList", "element2");
        List<String> list = stringRedisTemplate.opsForList().range("testList", 0, -1);
        log.info("向列表左侧插入元素，列表内容: {}", list);
    }

    /**
     * 测试从列表右侧弹出元素
     */
    @Test
    void testRightPopFromList() {
        stringRedisTemplate.opsForList().leftPush("testList", "element1");
        stringRedisTemplate.opsForList().leftPush("testList", "element2");
        String poppedElement = stringRedisTemplate.opsForList().rightPop("testList");
        log.info("从列表右侧弹出元素，弹出元素: {}", poppedElement);
    }

    /**
     * 测试向集合中添加元素
     */
    @Test
    void testAddToSet() {
        stringRedisTemplate.opsForSet().add("testSet", "element1", "element2");
        Set<String> set = stringRedisTemplate.opsForSet().members("testSet");
        log.info("向集合中添加元素，集合内容: {}", set);
    }

    /**
     * 测试从集合中移除元素
     */
    @Test
    void testRemoveFromSet() {
        stringRedisTemplate.opsForSet().add("testSet", "element1", "element2");
        Long removedCount = stringRedisTemplate.opsForSet().remove("testSet", "element1");
        log.info("从集合中移除元素，移除数量: {}", removedCount);
    }

    /**
     * 测试向有序集合中添加元素
     */
    @Test
    void testAddToZSet() {
        stringRedisTemplate.opsForZSet().add("testZSet", "element1", 1.0);
        stringRedisTemplate.opsForZSet().add("testZSet", "element2", 2.0);
        Set<String> zSet = stringRedisTemplate.opsForZSet().range("testZSet", 0, -1);
        log.info("向有序集合中添加元素，有序集合内容: {}", zSet);
    }

    /**
     * 测试从有序集合中移除元素
     */
    @Test
    void testRemoveFromZSet() {
        stringRedisTemplate.opsForZSet().add("testZSet", "element1", 1.0);
        stringRedisTemplate.opsForZSet().add("testZSet", "element2", 2.0);
        Long removedCount = stringRedisTemplate.opsForZSet().remove("testZSet", "element1");
        log.info("从有序集合中移除元素，移除数量: {}", removedCount);
    }
}