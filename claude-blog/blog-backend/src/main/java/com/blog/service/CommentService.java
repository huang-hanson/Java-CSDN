package com.blog.service;

import com.blog.entity.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByPostId(Long postId);
    Comment addComment(Comment comment);
    boolean deleteComment(Long id, Long userId);
}
