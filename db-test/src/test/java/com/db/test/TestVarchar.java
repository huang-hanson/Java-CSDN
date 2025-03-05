package com.db.test;

import com.db.test.entity.bo.StoreProduct;
import com.db.test.entity.bo.TestVarcharBO;
import com.db.test.mapper.TestVarcharMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestVarchar
 * @Description TODO
 * @date 2025/2/28 17:58
 **/
@Slf4j
@SpringBootTest
public class TestVarchar {

    @Resource
    private TestVarcharMapper testVarcharMapper;


    @Test
    void test_varchar() {
        TestVarcharBO testVarchar = new TestVarcharBO();
//        testVarchar.setField1("hello world");
        testVarchar.setField1("he");
        testVarchar.setField2("hello world");
//        testVarchar.setField2("he");
        try {
            testVarcharMapper.insert(testVarchar);
        } catch (Exception e) {
            log.error("入表异常:{0}", e);
        }

    }
}