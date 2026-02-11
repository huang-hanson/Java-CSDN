package com.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.Result;
import com.mall.entity.Product;
import com.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/list")
    public Result<List<Product>> getAllProducts() {
        List<Product> products = productService.getAll();
        return Result.success(products);
    }
    
    @GetMapping("/page")
    public Result<Page<Product>> getProductPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String keyword) {
        Page<Product> page = productService.getPage(pageNum, pageSize, categoryId, keyword);
        return Result.success(page);
    }
    
    @GetMapping("/{id}")
    public Result<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            return Result.success(product);
        }
        return Result.error("Product not found");
    }
    
    @GetMapping("/category/{categoryId}")
    public Result<List<Product>> getByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = productService.getByCategoryId(categoryId);
        return Result.success(products);
    }
    
    @PostMapping("/add")
    public Result<String> addProduct(@RequestBody Product product) {
        boolean success = productService.add(product);
        if (success) {
            return Result.success("Product added successfully");
        }
        return Result.error("Failed to add product");
    }
    
    @PutMapping("/update")
    public Result<String> updateProduct(@RequestBody Product product) {
        boolean success = productService.update(product);
        if (success) {
            return Result.success("Product updated successfully");
        }
        return Result.error("Failed to update product");
    }
    
    @DeleteMapping("/delete/{id}")
    public Result<String> deleteProduct(@PathVariable Long id) {
        boolean success = productService.delete(id);
        if (success) {
            return Result.success("Product deleted successfully");
        }
        return Result.error("Failed to delete product");
    }
    
}