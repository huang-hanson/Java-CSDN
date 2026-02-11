package com.mall.controller;

import com.mall.common.Result;
import com.mall.entity.Category;
import com.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/list")
    public Result<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAll();
        return Result.success(categories);
    }
    
    @GetMapping("/{id}")
    public Result<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        if (category != null) {
            return Result.success(category);
        }
        return Result.error("Category not found");
    }
    
    @GetMapping("/parent/{parentId}")
    public Result<List<Category>> getByParentId(@PathVariable Long parentId) {
        List<Category> categories = categoryService.getByParentId(parentId);
        return Result.success(categories);
    }
    
    @PostMapping("/add")
    public Result<String> addCategory(@RequestBody Category category) {
        boolean success = categoryService.add(category);
        if (success) {
            return Result.success("Category added successfully");
        }
        return Result.error("Failed to add category");
    }
    
    @PutMapping("/update")
    public Result<String> updateCategory(@RequestBody Category category) {
        boolean success = categoryService.update(category);
        if (success) {
            return Result.success("Category updated successfully");
        }
        return Result.error("Failed to update category");
    }
    
    @DeleteMapping("/delete/{id}")
    public Result<String> deleteCategory(@PathVariable Long id) {
        boolean success = categoryService.delete(id);
        if (success) {
            return Result.success("Category deleted successfully");
        }
        return Result.error("Failed to delete category");
    }
}