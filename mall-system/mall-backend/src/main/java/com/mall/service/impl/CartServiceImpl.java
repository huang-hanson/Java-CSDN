package com.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.entity.Cart;
import com.mall.mapper.CartMapper;
import com.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartMapper cartMapper;
    
    @Override
    public List<Cart> getByUserId(Long userId) {
        return cartMapper.selectCartWithProductByUserId(userId);
    }
    
    @Override
    public Cart getByUserIdAndProductId(Long userId, Long productId) {
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId, userId)
               .eq(Cart::getProductId, productId);
        return cartMapper.selectOne(wrapper);
    }
    
    @Override
    public boolean add(Cart cart) {
        Cart existCart = getByUserIdAndProductId(cart.getUserId(), cart.getProductId());
        if (existCart != null) {
            existCart.setQuantity(existCart.getQuantity() + cart.getQuantity());
            return cartMapper.updateById(existCart) > 0;
        }
        cart.setChecked(1);
        return cartMapper.insert(cart) > 0;
    }
    
    @Override
    public boolean update(Cart cart) {
        return cartMapper.updateById(cart) > 0;
    }
    
    @Override
    public boolean delete(Long id) {
        return cartMapper.deleteById(id) > 0;
    }
    
    @Override
    public boolean deleteByUserId(Long userId) {
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId, userId);
        return cartMapper.delete(wrapper) >= 0;
    }
    
    @Override
    public boolean updateQuantity(Long id, Integer quantity) {
        Cart cart = cartMapper.selectById(id);
        if (cart != null) {
            cart.setQuantity(quantity);
            return cartMapper.updateById(cart) > 0;
        }
        return false;
    }
    
    @Override
    public boolean updateChecked(Long id, Integer checked) {
        Cart cart = cartMapper.selectById(id);
        if (cart != null) {
            cart.setChecked(checked);
            return cartMapper.updateById(cart) > 0;
        }
        return false;
    }
}