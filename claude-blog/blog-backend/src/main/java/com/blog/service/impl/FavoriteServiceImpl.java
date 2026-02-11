package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.entity.Favorite;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.mapper.FavoriteMapper;
import com.blog.mapper.PostMapper;
import com.blog.mapper.UserMapper;
import com.blog.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addFavorite(Long userId, Long postId) {
        if (isFavorited(userId, postId)) {
            return false;
        }
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setPostId(postId);
        return favoriteMapper.insert(favorite) > 0;
    }

    @Override
    public boolean removeFavorite(Long userId, Long postId) {
        LambdaQueryWrapper<Favorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorite::getUserId, userId)
               .eq(Favorite::getPostId, postId);
        return favoriteMapper.delete(wrapper) > 0;
    }

    @Override
    public boolean isFavorited(Long userId, Long postId) {
        if (userId == null || postId == null) {
            return false;
        }
        LambdaQueryWrapper<Favorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorite::getUserId, userId)
               .eq(Favorite::getPostId, postId);
        return favoriteMapper.selectCount(wrapper) > 0;
    }

    @Override
    public List<Favorite> getMyFavorites(Long userId) {
        LambdaQueryWrapper<Favorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Favorite::getUserId, userId)
               .orderByDesc(Favorite::getCreateTime);
        List<Favorite> favorites = favoriteMapper.selectList(wrapper);

        // Fill post info
        for (Favorite favorite : favorites) {
            Post post = postMapper.selectById(favorite.getPostId());
            if (post != null) {
                if (post.getAuthorId() != null) {
                    User author = userMapper.selectById(post.getAuthorId());
                    if (author != null) {
                        post.setAuthorName(author.getNickname() != null ? author.getNickname() : author.getUsername());
                    }
                }
                favorite.setPost(post);
            }
        }

        return favorites.stream()
                .filter(f -> f.getPost() != null)
                .collect(Collectors.toList());
    }
}
