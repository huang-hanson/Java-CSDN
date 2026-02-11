package com.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.entity.Product;
import java.util.List;

public interface ProductService {
    Product getById(Long id);
    List<Product> getByCategoryId(Long categoryId);
    Page<Product> getPage(Integer pageNum, Integer pageSize, Long categoryId, String keyword);
    List<Product> getAll();
    boolean add(Product product);
    boolean update(Product product);
    boolean delete(Long id);
}