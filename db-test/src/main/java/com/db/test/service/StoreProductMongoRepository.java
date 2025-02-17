package com.db.test.service;

import com.db.test.entity.dto.MongoStoreProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName StoreProductMongoRepository
 * @Description 商品Mongo Repository
 * @date 2025/2/17 17:53
 **/
@Repository
public interface StoreProductMongoRepository extends MongoRepository<MongoStoreProduct, String> {
}
