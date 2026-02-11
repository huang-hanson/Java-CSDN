package com.mall.controller;

import com.mall.common.Result;
import com.mall.entity.Cart;
import com.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @GetMapping("/list/{userId}")
    public Result<List<Cart>> getCartByUserId(@PathVariable Long userId) {
        List<Cart> carts = cartService.getByUserId(userId);
        return Result.success(carts);
    }
    
    @PostMapping("/add")
    public Result<String> addToCart(@RequestBody Cart cart) {
        boolean success = cartService.add(cart);
        if (success) {
            return Result.success("Added to cart successfully");
        }
        return Result.error("Failed to add to cart");
    }
    
    @PutMapping("/quantity/{id}")
    public Result<String> updateQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        boolean success = cartService.updateQuantity(id, quantity);
        if (success) {
            return Result.success("Quantity updated successfully");
        }
        return Result.error("Failed to update quantity");
    }
    
    @PutMapping("/checked/{id}")
    public Result<String> updateChecked(@PathVariable Long id, @RequestParam Integer checked) {
        boolean success = cartService.updateChecked(id, checked);
        if (success) {
            return Result.success("Checked status updated successfully");
        }
        return Result.error("Failed to update checked status");
    }
    
    @DeleteMapping("/delete/{id}")
    public Result<String> deleteCartItem(@PathVariable Long id) {
        boolean success = cartService.delete(id);
        if (success) {
            return Result.success("Cart item deleted successfully");
        }
        return Result.error("Failed to delete cart item");
    }
    
    @DeleteMapping("/clear/{userId}")
    public Result<String> clearCart(@PathVariable Long userId) {
        boolean success = cartService.deleteByUserId(userId);
        if (success) {
            return Result.success("Cart cleared successfully");
        }
        return Result.error("Failed to clear cart");
    }
}