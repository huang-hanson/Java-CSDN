package com.blog.service;

import com.blog.entity.Favorite;
import java.util.List;

public interface FavoriteService {
    boolean addFavorite(Long userId, Long postId);
    boolean removeFavorite(Long userId, Long postId);
    boolean isFavorited(Long userId, Long postId);
    List<Favorite> getMyFavorites(Long userId);
}
