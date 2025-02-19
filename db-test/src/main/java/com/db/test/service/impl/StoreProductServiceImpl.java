package com.db.test.service.impl;

import cn.hutool.core.date.StopWatch;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csdn.dev.common.util.JacksonUtils;
import com.db.test.entity.bo.StoreProduct;
import com.db.test.entity.dto.MongoStoreProduct;
import com.db.test.entity.dto.StoreProductEsDTO;
import com.db.test.entity.request.StoreProductRequest;
import com.db.test.mapper.StoreProductMapper;
import com.db.test.service.StoreProductMongoRepository;
import com.db.test.service.StoreProductService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StoreProductServiceImpl
 * @Description 商品服务接口实现类
 * @date 2025/2/17 14:26
 **/
@Slf4j
@Data
@Service
public class StoreProductServiceImpl extends ServiceImpl<StoreProductMapper, StoreProduct> implements StoreProductService {

    @Resource
    private StoreProductMapper storeProductMapper;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "elasticsearchRestTemplate")
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private StoreProductMongoRepository storeProductMongoRepository;

    private static final String REDIS_KEY = "storeProduct:key";

    @Value("${es.storeProduct.indexName:store_product_info_v2}")
    public String storeProductEsIndexName = "store_product_info_v2";

    @Value("${es.storeProduct.pageSize:500}")
    private int storeProductEsListPageSize = 500;

    @Override
    public void insertData(StoreProductRequest storeProductRequest) {
        // 1.插入mysql
        StoreProduct storeProduct = new StoreProduct();
        BeanUtils.copyProperties(storeProductRequest, storeProduct);
        storeProduct.setActivity(JacksonUtils.jsonEncode(storeProductRequest.getActivity()));
        storeProduct.setAttr(JacksonUtils.jsonEncode(storeProductRequest.getAttr()));
        storeProduct.setAttrValue(JacksonUtils.jsonEncode(storeProductRequest.getAttrValue()));
        storeProduct.setCouponIds(JacksonUtils.jsonEncode(storeProductRequest.getCouponIds()));
        storeProductMapper.insert(storeProduct);
        log.warn("数据已经插入mysql数据库：{}", JacksonUtils.jsonEncode(storeProduct));

        // 2.插入redis
        stringRedisTemplate.opsForValue().set(REDIS_KEY + storeProduct.getId(), JacksonUtils.jsonEncode(storeProduct));
        log.warn("数据已经插入redis数据库：{}", JacksonUtils.jsonEncode(storeProduct));

        // 3.插入mongo
        MongoStoreProduct mongoStoreProduct = new MongoStoreProduct();
        BeanUtils.copyProperties(storeProduct, mongoStoreProduct);
        mongoStoreProduct.setId(storeProduct.getId() + "");
        try {
            storeProductMongoRepository.save(mongoStoreProduct);
            log.warn("数据已经插入mongo数据库：{}", JacksonUtils.jsonEncode(mongoStoreProduct));
        } catch (Exception e) {
            log.error("数据插入mongo数据库失败,失败原因：{}", e);
        }

        // 4.插入es
        StoreProductEsDTO storeProductEsDTO = new StoreProductEsDTO();
        BeanUtils.copyProperties(storeProduct, storeProductEsDTO);
        storeProductEsDTO.setId(storeProduct.getId() + "");
        // 创建客户端
        List<IndexQuery> queries = new ArrayList<>();
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId(storeProduct.getId() + "");
        indexQuery.setObject(storeProductEsDTO);
        queries.add(indexQuery);
        try {
            elasticsearchRestTemplate.bulkIndex(queries, StoreProductEsDTO.class);
            log.warn("数据已经插入es数据库：{}", JacksonUtils.jsonEncode(storeProductEsDTO));
        } catch (Exception e) {
            log.error("数据插入es数据库失败,失败原因：{}", e);
        }
    }

    /**
     * @return 通过四种方式获取数据
     */
    @Override
    public Map<String, Object> getData(Integer id) {
        Map<String, Object> result = new HashMap<>();
        // 1.从mysql获取数据
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("mysql查询数据开始");
        StoreProduct storeProduct = storeProductMapper.selectById(id);
        result.put("mysql", storeProduct);
        stopWatch.stop();

        // 2.从redis获取数据
        stopWatch.start("redis查询数据开始");
        String redisData = stringRedisTemplate.opsForValue().get(REDIS_KEY + id);
        result.put("redis", JacksonUtils.jsonDecode(redisData, StoreProduct.class));
        stopWatch.stop();

        // 3.从mongo获取数据
        stopWatch.start("mongo查询数据开始");
        Optional<MongoStoreProduct> optional = storeProductMongoRepository.findById(String.valueOf(id));
        if (optional.isPresent()) {
            MongoStoreProduct mongoStoreProduct = optional.get();
            result.put("mongo", mongoStoreProduct);
        }
        stopWatch.stop();

        // 4.从es获取数据
        stopWatch.start("es查询数据开始");
        StoreProductEsDTO storeProductEsDTO = elasticsearchRestTemplate.get(String.valueOf(id), StoreProductEsDTO.class);
        result.put("es", storeProductEsDTO);
        stopWatch.stop();

        log.error("查询数据耗时：{}", stopWatch.prettyPrint(TimeUnit.MILLISECONDS));

        return result;
    }

}