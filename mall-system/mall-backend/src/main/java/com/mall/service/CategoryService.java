package com.mall.service;

import com.mall.entity.Category;
import java.util.List;

public interface CategoryService {
    Category getById(Long id);
    List<Category> getAll();
    List<Category> getByParentId(Long parentId);
    boolean add(Category category);
    boolean update(Category category);
    boolean delete(Long id);
}