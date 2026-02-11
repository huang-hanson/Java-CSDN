package com.mall.service;

import com.mall.entity.Cart;
import java.util.List;

public interface CartService {
    List<Cart> getByUserId(Long userId);
    Cart getByUserIdAndProductId(Long userId, Long productId);
    boolean add(Cart cart);
    boolean update(Cart cart);
    boolean delete(Long id);
    boolean deleteByUserId(Long userId);
    boolean updateQuantity(Long id, Integer quantity);
    boolean updateChecked(Long id, Integer checked);
}