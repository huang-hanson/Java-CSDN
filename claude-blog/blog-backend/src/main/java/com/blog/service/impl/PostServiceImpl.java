package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.entity.Favorite;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.mapper.FavoriteMapper;
import com.blog.mapper.PostMapper;
import com.blog.mapper.UserMapper;
import com.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public Page<Post> getPostList(int pageNum, int pageSize, String keyword) {
        Page<Post> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getStatus, 1);
        if (StringUtils.hasText(keyword)) {
            wrapper.like(Post::getTitle, keyword)
                   .or()
                   .like(Post::getContent, keyword);
        }
        wrapper.orderByDesc(Post::getCreateTime);
        postMapper.selectPage(page, wrapper);

        // Fill author name
        page.getRecords().forEach(this::fillAuthorName);

        return page;
    }

    @Override
    public Post getPostById(Long id, Long currentUserId) {
        Post post = postMapper.selectById(id);
        if (post != null) {
            fillAuthorName(post);
            // Check if favorited
            if (currentUserId != null) {
                LambdaQueryWrapper<Favorite> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Favorite::getUserId, currentUserId)
                       .eq(Favorite::getPostId, id);
                post.setFavorited(favoriteMapper.selectCount(wrapper) > 0);
            }
        }
        return post;
    }

    @Override
    public Post createPost(Post post) {
        post.setViewCount(0);
        post.setLikeCount(0);
        post.setCommentCount(0);
        if (post.getStatus() == null) {
            post.setStatus(1);
        }
        if (post.getSummary() == null && post.getContent() != null) {
            String summary = post.getContent();
            if (summary.length() > 200) {
                summary = summary.substring(0, 200) + "...";
            }
            post.setSummary(summary.replaceAll("#|\\*|`", ""));
        }
        postMapper.insert(post);
        return post;
    }

    @Override
    public Post updatePost(Post post) {
        Post existPost = postMapper.selectById(post.getId());
        if (existPost != null && existPost.getAuthorId().equals(post.getAuthorId())) {
            if (post.getSummary() == null && post.getContent() != null) {
                String summary = post.getContent();
                if (summary.length() > 200) {
                    summary = summary.substring(0, 200) + "...";
                }
                post.setSummary(summary.replaceAll("#|\\*|`", ""));
            }
            postMapper.updateById(post);
            return post;
        }
        return null;
    }

    @Override
    public boolean deletePost(Long id, Long userId) {
        Post post = postMapper.selectById(id);
        if (post != null && post.getAuthorId().equals(userId)) {
            return postMapper.deleteById(id) > 0;
        }
        return false;
    }

    @Override
    public List<Post> getMyPosts(Long userId) {
        LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Post::getAuthorId, userId)
               .orderByDesc(Post::getCreateTime);
        List<Post> posts = postMapper.selectList(wrapper);
        posts.forEach(this::fillAuthorName);
        return posts;
    }

    @Override
    public void incrementViewCount(Long id) {
        Post post = postMapper.selectById(id);
        if (post != null) {
            post.setViewCount(post.getViewCount() + 1);
            postMapper.updateById(post);
        }
    }

    private void fillAuthorName(Post post) {
        if (post.getAuthorId() != null) {
            User author = userMapper.selectById(post.getAuthorId());
            if (author != null) {
                post.setAuthorName(author.getNickname() != null ? author.getNickname() : author.getUsername());
            }
        }
    }
}
