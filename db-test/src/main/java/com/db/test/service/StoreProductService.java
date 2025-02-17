package com.db.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.db.test.entity.bo.StoreProduct;
import com.db.test.entity.request.StoreProductRequest;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName StoreProductService
 * @Description 商品服务接口
 * @date 2025/2/17 14:25
 **/
public interface StoreProductService extends IService<StoreProduct> {

    void insertData(StoreProductRequest storeProductRequest);
}
