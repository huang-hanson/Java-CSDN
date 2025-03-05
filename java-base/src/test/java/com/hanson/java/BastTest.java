package com.hanson.java;

import com.csdn.dev.common.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName BastTest
 * @Description TODO
 * @date 2025/2/25 15:34
 **/
@Slf4j
@SpringBootTest
public class BastTest {


    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void test_1() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", null);
        String s = JacksonUtils.jsonEncode(map);
        log.info("s: {}", s);

        HashMap<String, Object> map2 = JacksonUtils.jsonDecode(s);
        log.info("map2: {}", map2);
        Object o = map.get("c");
        if (o == null) {
            log.info("o is null");
        }
    }

    @Test
    void test_2() {
        if (stringRedisTemplate.hasKey("track_media_0ed5103f88f9b3417c8cba24b8cc268d_0ed5103f88f9b3417c8cba24b8cc268d_0_0_0_0")) {
            stringRedisTemplate.delete("track_media_0ed5103f88f9b3417c8cba24b8cc268d_0ed5103f88f9b3417c8cba24b8cc268d_0_0_0_0");
            log.info("track_media_0ed5103f88f9b3417c8cba24b8cc268d_0ed5103f88f9b3417c8cba24b8cc268d_0_0_0_0 删除成功");
        }
    }
}