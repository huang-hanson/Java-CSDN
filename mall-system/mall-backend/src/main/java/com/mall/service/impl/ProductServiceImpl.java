package com.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.entity.Product;
import com.mall.mapper.ProductMapper;
import com.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductMapper productMapper;
    
    @Override
    public Product getById(Long id) {
        return productMapper.selectById(id);
    }
    
    @Override
    public List<Product> getByCategoryId(Long categoryId) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getCategoryId, categoryId)
               .eq(Product::getStatus, 1);
        return productMapper.selectList(wrapper);
    }
    
    @Override
    public Page<Product> getPage(Integer pageNum, Integer pageSize, Long categoryId, String keyword) {
        Page<Product> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getStatus, 1);
        if (categoryId != null && categoryId > 0) {
            wrapper.eq(Product::getCategoryId, categoryId);
        }
        if (StringUtils.hasText(keyword)) {
            wrapper.like(Product::getName, keyword);
        }
        return productMapper.selectPage(page, wrapper);
    }
    
    @Override
    public List<Product> getAll() {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getStatus, 1);
        return productMapper.selectList(wrapper);
    }
    
    @Override
    public boolean add(Product product) {
        product.setStatus(1);
        return productMapper.insert(product) > 0;
    }
    
    @Override
    public boolean update(Product product) {
        return productMapper.updateById(product) > 0;
    }
    
    @Override
    public boolean delete(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setStatus(0);
        return productMapper.updateById(product) > 0;
    }
}