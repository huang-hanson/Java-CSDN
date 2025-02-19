package com.db.test;

import com.db.test.entity.dto.StoreProductEsDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestES
 * @Description es 测试类
 * @date 2025/2/18 19:41
 **/
@Slf4j
@SpringBootTest
public class TestES {

    @Resource(name = "elasticsearchRestTemplate")
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 生成模拟的 StoreProductEsDTO 对象
     * @return 模拟对象
     */
    private StoreProductEsDTO generateMockProduct(String id) {
        StoreProductEsDTO product = new StoreProductEsDTO();
        product.setId(id);
        product.setImage("https://example.com/image.jpg");
        product.setSliderImage("https://example.com/slider_image.jpg");
        product.setStoreName("11测试店铺商品" + id);
        product.setStoreInfo("这是一个测试用的店铺商品信息");
        product.setKeyword("测试商品");
        product.setCateId("1001");
        product.setUnitName("件");
        product.setSort(1);
        product.setIsHot(true);
        product.setIsBenefit(true);
        product.setIsBest(true);
        product.setIsNew(true);
        product.setIsGood(true);
        product.setGiveIntegral(10);
        product.setIsSub(false);
        product.setFicti(1);
        product.setTempId(1);
        product.setSpecType(true);
        product.setActivity("{\"name\":\"测试活动\"}");
        product.setAttr("{\"color\":\"red\"}");
        product.setAttrValue("{\"size\":\"L\"}");
        product.setContent("商品详细内容描述");
        product.setCouponIds("{\"id\":\"C001\"}");
        product.setFlatPattern("{\"mode\":\"平铺\"}");
        return product;
    }

    /**
     * 插入单条文档
     */
    @Test
    void testInsertDocument() {
        StoreProductEsDTO product = generateMockProduct("990");
        StoreProductEsDTO savedProduct = elasticsearchRestTemplate.save(product);
        log.info("插入文档结果: {}", savedProduct);
    }

    /**
     * 批量插入文档
     */
    @Test
    void testBulkInsertDocuments() {
        List<StoreProductEsDTO> products = Arrays.asList(generateMockProduct("992"), generateMockProduct("993"));
        Iterable<StoreProductEsDTO> savedProducts = elasticsearchRestTemplate.save(products);
        savedProducts.forEach(product -> log.info("批量插入文档结果: {}", product));
    }

    /**
     * 根据 ID 删除文档
     */
    @Test
    void testDeleteDocument() {
        String id = "997";
        elasticsearchRestTemplate.delete(id, StoreProductEsDTO.class);
        log.info("删除 ID 为 {} 的文档", id);
    }

    /**
     * 根据 ID 更新文档
     */
    @Test
    void testUpdateDocument() {
        StoreProductEsDTO product = generateMockProduct("994");
        product.setStoreName("更新后的测试店铺商品");
        StoreProductEsDTO updatedProduct = elasticsearchRestTemplate.save(product);
        log.info("更新文档结果: {}", updatedProduct);
    }

    /**
     * 查询单条文档
     */
    @Test
    void testSearchSingleDocument() {
        String id = "992";
        StoreProductEsDTO product = elasticsearchRestTemplate.get(id, StoreProductEsDTO.class);
        if (product != null) {
            log.info("查询到的文档: {}", product);
        } else {
            log.info("未查询到 ID 为 {} 的文档", id);
        }
    }

    /**
     * 查询所有文档
     */
    @Test
    void testSearchAllDocuments() {
        Query query = new CriteriaQuery(new Criteria());
        SearchHits<StoreProductEsDTO> searchHits = elasticsearchRestTemplate.search(query, StoreProductEsDTO.class);
        searchHits.forEach(hit -> log.info("查询到的文档: {}", hit.getContent()));
    }

    /**
     * 分页查询文档
     */
    @Test
    void testSearchDocumentsByPage() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Query query = new CriteriaQuery(new Criteria()).setPageable(pageable);

        SearchHits<StoreProductEsDTO> searchHits = elasticsearchRestTemplate.search(query, StoreProductEsDTO.class);
        log.info("当前页文档数量: {}", searchHits.getSearchHits().size());

        // 修正遍历部分
        List<org.springframework.data.elasticsearch.core.SearchHit<StoreProductEsDTO>> searchHitList = searchHits.getSearchHits();
        for (org.springframework.data.elasticsearch.core.SearchHit<StoreProductEsDTO> hit : searchHitList) {
            log.info("分页查询到的文档: {}", hit.getContent());
        }
    }

    /**
     * 根据条件查询文档
     */
    @Test
    void testSearchDocumentsByCondition() {
        Criteria criteria = new Criteria("storeName").is("测试店铺商品");
        Query query = new CriteriaQuery(criteria);
        SearchHits<StoreProductEsDTO> searchHits = elasticsearchRestTemplate.search(query, StoreProductEsDTO.class);
        searchHits.forEach(hit -> log.info("根据条件查询到的文档: {}", hit.getContent()));
    }
}