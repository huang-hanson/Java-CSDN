package com.db.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.db.test.entity.bo.StoreProduct;
import com.db.test.mapper.StoreProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestMySQL
 * @Description Mysql api
 * @date 2025/2/18 15:01
 **/
@Slf4j
@SpringBootTest
public class TestMySQL {

    @Resource
    private StoreProductMapper storeProductMapper;

    /**
     * @param num 生成num条模拟数据
     * @return
     */
    private static List<StoreProduct> getStoreProduct(Integer num) {
        List<StoreProduct> result = new ArrayList<>();
        StoreProduct storeProduct = new StoreProduct();
        for (int i = 0; i < num; i++) {
            storeProduct.setId(999 + i).
                    setImage("https://www.baidu.com/img/bd_logo1.png").
                    setSliderImage("https://www.baidu.com/img/bd_logo1.png")
                    .setStoreName("测试商品" + i)
                    .setStoreInfo("测试商品")
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
            result.add(storeProduct);
        }
        return result;
    }

    /**
     * 插入单条数据
     */
    @Test
    void test_insert() {
        StoreProduct storeProduct = getStoreProduct(1).get(0);
        storeProductMapper.insert(storeProduct);
    }

    /**
     * 按照id删除
     */
    @Test
    void test_deleteById() {
        storeProductMapper.deleteById(999);
    }

    /**
     * 多个条件删除
     */
    @Test
    void test_deleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        // 添加多个条件
        columnMap.put("id", 999);
        columnMap.put("store_name", "测试商品");
        columnMap.put("is_hot", true);
        storeProductMapper.deleteByMap(columnMap);
    }

    /**
     * 构建wrapper语句删除
     */
    @Test
    void test_deleteByWrapper() {
        // 创建 QueryWrapper 对象
        QueryWrapper<StoreProduct> queryWrapper = new QueryWrapper<>();
        // 添加删除条件，例如删除 id 为 "999" 的记录,并且storeName 为 "测试商品" 的记录
        queryWrapper.eq("id", 999);
        storeProductMapper.delete(queryWrapper);
    }

    /**
     * 构建wrapper语句删除
     */
    @Test
    void test_deleteByLambdaWrapper() {
        // 创建 LambdaQueryWrapper 对象
        LambdaQueryWrapper<StoreProduct> queryWrapper = new LambdaQueryWrapper<>();
        // 添加删除条件，例如删除 id 为 "999" 的记录,并且storeName 为 "测试商品" 的记录
        queryWrapper.eq(StoreProduct::getId, 999);
        storeProductMapper.delete(queryWrapper);
    }

    /**
     * 批量删除
     */
    @Test
    void test_deleteBatchIds() {
        storeProductMapper.deleteBatchIds(Arrays.asList(999, 1000));
    }

    /**
     * 更新数据
     */
    @Test
    void test_updateById() {
        StoreProduct storeProduct = getStoreProduct(1).get(0);
        storeProduct.setStoreName("商品名字更新啦~");
        storeProductMapper.updateById(storeProduct);
    }

    /**
     * 构建wrapper语句更新
     */
    @Test
    void test_updateByWrapper() {
        // 创建 UpdateWrapper 对象
        UpdateWrapper<StoreProduct> queryWrapper = new UpdateWrapper<>();
        // 添加更新条件，例如更新 id 为 "999"
        queryWrapper.eq("id", 999);
        queryWrapper.set("store_name", "商品名字再次更新啦~");
        storeProductMapper.update(null, queryWrapper);
    }

    /**
     * 构建LambdaWrapper语句更新
     */
    @Test
    void test_updateByLambdaWrapper() {
        // 创建 UpdateWrapper 对象
        LambdaUpdateWrapper<StoreProduct> queryWrapper = new LambdaUpdateWrapper<>();
        // 添加更新条件，例如更新 id 为 "999"
        queryWrapper.eq(StoreProduct::getId, 999);
        queryWrapper.set(StoreProduct::getStoreName, "商品名字再再次更新啦~");
        storeProductMapper.update(null, queryWrapper);
    }

    /**
     * 通过id查找
     */
    @Test
    void test_selectById() {
        StoreProduct storeProduct = storeProductMapper.selectById(999);
        log.info("查询结果：{}", storeProduct);
    }

    /**
     * 通过id集合查找
     */
    @Test
    void test_selectBatchIds() {
        List<StoreProduct> storeProducts = storeProductMapper.selectBatchIds(Arrays.asList(1, 2));
        for (StoreProduct storeProduct : storeProducts) {
            log.info("查询结果：{}", storeProduct);
        }
    }

    /**
     * 通过map查找
     */
    @Test
    void test_selectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        // 添加多个条件
        columnMap.put("store_info", "测试商品");
        columnMap.put("is_hot", true);
        List<StoreProduct> storeProducts = storeProductMapper.selectByMap(columnMap);
        for (StoreProduct storeProduct : storeProducts) {
            log.info("查询结果：{}", storeProduct);
        }
    }

    /**
     * 根据条件查一个
     *
     * 注意，如果有多个满足条件的数据，代码会报错：One record is expected, but the query result is multiple records
     */
    @Test
    void test_selectOne() {
        QueryWrapper<StoreProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 999);
        StoreProduct storeProduct = storeProductMapper.selectOne(queryWrapper);
        log.info("查询结果：{}", storeProduct);
    }

    /**
     * 按照条件查询count总数
     */
    @Test
    void test_selectCount() {
        QueryWrapper<StoreProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_name", "新款智能手机");
        Long count = storeProductMapper.selectCount(queryWrapper);
        log.info("查询结果有：{} 条", count);
    }

    /**
     * 列表查询
     */
    @Test
    void test_selectList() {
        QueryWrapper<StoreProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_name", "新款智能手机");
        List<StoreProduct> storeProducts = storeProductMapper.selectList(queryWrapper);
        for (StoreProduct storeProduct : storeProducts) {
            log.info("查询结果：{}", storeProduct);
        }
    }

    /**
     * 查询结果为map
     */
    @Test
    void test_selectMaps() {
        QueryWrapper<StoreProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("store_info", "测试商品");
        List<Map<String, Object>> maps = storeProductMapper.selectMaps(queryWrapper);
        for (Map<String, Object> map : maps) {
            log.info("查询结果：{}", map);
        }
    }

    /**
     * 分页查询
     */
    @Test
    void test_selectPage() {
        // 创建分页对象，指定当前页码和每页记录数
        LambdaQueryWrapper<StoreProduct> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StoreProduct::getStoreName, "新款智能手机");
        Page<StoreProduct> page = new Page<>(1, 10);
        // 调用 selectPage 方法进行分页查询
        IPage<StoreProduct> resultPage = storeProductMapper.selectPage(page, lqw);
        log.info("当前页码：{}，每页记录数：{}，总页数：{}，总记录数：{}", resultPage.getCurrent(), resultPage.getSize(),resultPage.getPages(), resultPage.getTotal());
        for (StoreProduct storeProduct : resultPage.getRecords()) {
            log.info("查询结果：{}", storeProduct);
        }
    }
}