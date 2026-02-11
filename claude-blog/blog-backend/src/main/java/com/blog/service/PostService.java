package com.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.entity.Post;
import java.util.List;

public interface PostService {
    Page<Post> getPostList(int pageNum, int pageSize, String keyword);
    Post getPostById(Long id, Long currentUserId);
    Post createPost(Post post);
    Post updatePost(Post post);
    boolean deletePost(Long id, Long userId);
    List<Post> getMyPosts(Long userId);
    void incrementViewCount(Long id);
}
