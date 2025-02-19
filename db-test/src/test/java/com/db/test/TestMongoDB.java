package com.db.test;

import com.db.test.entity.dto.MongoStoreProduct;
import com.db.test.service.StoreProductMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestMongoDB
 * @Description MongoDB api
 * @date 2025/2/18 14:42
 **/
@Slf4j
@SpringBootTest
public class TestMongoDB {

    @Resource
    private StoreProductMongoRepository storeProductMongoRepository;

    /**
     * 生成模拟数据
     *
     * @param num 生成的数量
     * @return 模拟数据列表
     */
    private List<MongoStoreProduct> getStoreProduct(Integer num) {
        List<MongoStoreProduct> result = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            MongoStoreProduct mongoStoreProduct = new MongoStoreProduct();
            mongoStoreProduct.setId(String.valueOf(999 + i))
                    .setImage("https://www.baidu.com/img/bd_logo1.png")
                    .setSliderImage("https://www.baidu.com/img/bd_logo1.png")
                    .setStoreName("测试商品" + i)
                    .setStoreInfo("测试商品" + i)
                    .setKeyword("测试商品")
                    .setCateId("1")
                    .setUnitName("件")
                    .setSort(1)
                    .setIsHot(true)
                    .setIsBenefit(true)
                    .setIsBest(true)
                    .setIsNew(true)
                    .setIsGood(true)
                    .setGiveIntegral(1)
                    .setIsSub(true)
                    .setFicti(1)
                    .setTempId(1)
                    .setSpecType(true)
                    .setActivity("{\"test\":\"test\"}")
                    .setAttr("{\"test\":\"test\"}")
                    .setAttrValue("{\"test\":\"test\"}")
                    .setContent("{\"test\":\"test\"}")
                    .setCouponIds("{\"test\":\"test\"}")
                    .setFlatPattern("{\"test\":\"test\"}");
            result.add(mongoStoreProduct);
        }
        return result;
    }

    /**
     * 插入单条数据  id相同时，内容会进行覆盖
     */
    @Test
    void test_insert() {
        MongoStoreProduct mongoStoreProduct = getStoreProduct(1).get(0);
        MongoStoreProduct save = storeProductMongoRepository.save(mongoStoreProduct);
        log.info("插入单条数据，结果: {}", save);
    }

    /**
     * 插入多条数据  id相同时，内容会进行覆盖
     */
    @Test
    void test_insertMultiple() {
        List<MongoStoreProduct> storeProduct = getStoreProduct(3);
        List<MongoStoreProduct> mongoStoreProducts = storeProductMongoRepository.saveAll(storeProduct);
        mongoStoreProducts.forEach(product -> log.info("插入多条数据，结果: {}", product));
    }

    /**
     * 根据 ID 查询单条数据
     */
    @Test
    void test_findById() {
        Optional<MongoStoreProduct> mongoStoreProductOpt = storeProductMongoRepository.findById(String.valueOf(999));
        if (mongoStoreProductOpt.isPresent()) {
            log.info("根据 ID 查询单条数据，结果: {}", mongoStoreProductOpt.get());
        } else {
            log.info("未找到对应 ID 的数据");
        }
    }

    /**
     * 查询所有数据
     */
    @Test
    void test_findAll() {
        Iterable<MongoStoreProduct> mongoStoreProducts = storeProductMongoRepository.findAll();
        mongoStoreProducts.forEach(product -> log.info("查询所有数据，结果: {}", product));
    }

    /**
     * 根据 ID 删除单条数据
     */
    @Test
    void test_deleteById() {
        storeProductMongoRepository.deleteById(String.valueOf(999));
        log.info("根据 ID 删除单条数据，删除完成");
    }

    /**
     * 删除所有数据
     */
    @Test
    void test_deleteAll() {
        storeProductMongoRepository.deleteAll();
        log.info("删除所有数据，删除完成");
    }

    /**
     * 分页查询数据
     */
    @Test
    void test_findAllByPage() {
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "id"));
        Page<MongoStoreProduct> productPage  = storeProductMongoRepository.findAll(pageRequest);
        log.info("当前页码: {}, 每页记录数: {}, 总记录数: {}, 总页数: {}",
                productPage.getNumber(), productPage.getSize(), productPage.getTotalElements(), productPage.getTotalPages());
        // 当前页码: 0, 每页记录数: 2, 总记录数: 23, 总页数: 12
        productPage.getContent().forEach(product -> log.info("分页查询数据，结果: {}", product));
    }
}