package com.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.entity.Category;
import com.mall.mapper.CategoryMapper;
import com.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public Category getById(Long id) {
        return categoryMapper.selectById(id);
    }
    
    @Override
    public List<Category> getAll() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, 1)
               .orderByAsc(Category::getSortOrder);
        return categoryMapper.selectList(wrapper);
    }
    
    @Override
    public List<Category> getByParentId(Long parentId) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId, parentId)
               .eq(Category::getStatus, 1)
               .orderByAsc(Category::getSortOrder);
        return categoryMapper.selectList(wrapper);
    }
    
    @Override
    public boolean add(Category category) {
        category.setStatus(1);
        return categoryMapper.insert(category) > 0;
    }
    
    @Override
    public boolean update(Category category) {
        return categoryMapper.updateById(category) > 0;
    }
    
    @Override
    public boolean delete(Long id) {
        Category category = new Category();
        category.setId(id);
        category.setStatus(0);
        return categoryMapper.updateById(category) > 0;
    }
}