package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.mapper.CommentMapper;
import com.blog.mapper.PostMapper;
import com.blog.mapper.UserMapper;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getPostId, postId)
               .orderByAsc(Comment::getCreateTime);
        List<Comment> allComments = commentMapper.selectList(wrapper);

        // Fill usernames
        for (Comment comment : allComments) {
            fillUserInfo(comment);
        }

        // Build tree structure
        Map<Long, List<Comment>> childrenMap = allComments.stream()
                .filter(c -> c.getParentId() != null && c.getParentId() > 0)
                .collect(Collectors.groupingBy(Comment::getParentId));

        List<Comment> rootComments = allComments.stream()
                .filter(c -> c.getParentId() == null || c.getParentId() == 0)
                .collect(Collectors.toList());

        for (Comment root : rootComments) {
            root.setReplies(childrenMap.getOrDefault(root.getId(), new ArrayList<>()));
        }

        return rootComments;
    }

    @Override
    public Comment addComment(Comment comment) {
        if (comment.getParentId() == null) {
            comment.setParentId(0L);
        }
        commentMapper.insert(comment);

        // Update comment count
        Post post = postMapper.selectById(comment.getPostId());
        if (post != null) {
            post.setCommentCount(post.getCommentCount() + 1);
            postMapper.updateById(post);
        }

        Comment saved = commentMapper.selectById(comment.getId());
        fillUserInfo(saved);
        return saved;
    }

    @Override
    public boolean deleteComment(Long id, Long userId) {
        Comment comment = commentMapper.selectById(id);
        if (comment != null && comment.getUserId().equals(userId)) {
            int result = commentMapper.deleteById(id);
            if (result > 0) {
                // Update comment count
                Post post = postMapper.selectById(comment.getPostId());
                if (post != null) {
                    post.setCommentCount(Math.max(0, post.getCommentCount() - 1));
                    postMapper.updateById(post);
                }
                return true;
            }
        }
        return false;
    }

    private void fillUserInfo(Comment comment) {
        if (comment.getUserId() != null) {
            User user = userMapper.selectById(comment.getUserId());
            if (user != null) {
                comment.setUsername(user.getNickname() != null ? user.getNickname() : user.getUsername());
            }
        }
        if (comment.getReplyUserId() != null) {
            User replyUser = userMapper.selectById(comment.getReplyUserId());
            if (replyUser != null) {
                comment.setReplyUsername(replyUser.getNickname() != null ? replyUser.getNickname() : replyUser.getUsername());
            }
        }
    }
}
