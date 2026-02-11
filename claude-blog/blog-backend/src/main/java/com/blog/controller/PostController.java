package com.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.common.Result;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.service.PostService;
import com.blog.service.UserService;
import com.blog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/list")
    public Result<Map<String, Object>> getPostList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        Page<Post> page = postService.getPostList(pageNum, pageSize, keyword);
        Map<String, Object> data = new HashMap<>();
        data.put("list", page.getRecords());
        data.put("total", page.getTotal());
        data.put("pageNum", pageNum);
        data.put("pageSize", pageSize);
        return Result.success(data);
    }

    @GetMapping("/{id}")
    public Result<Post> getPostById(@PathVariable Long id, HttpServletRequest request) {
        Long currentUserId = getCurrentUserId(request);
        Post post = postService.getPostById(id, currentUserId);
        if (post != null) {
            postService.incrementViewCount(id);
            return Result.success(post);
        }
        return Result.error("Post not found");
    }

    @PostMapping
    public Result<Post> createPost(@RequestBody Post post, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        if (userId == null) {
            return Result.error("Please login first");
        }
        post.setAuthorId(userId);
        Post created = postService.createPost(post);
        return Result.success("Post created successfully", created);
    }

    @PutMapping("/{id}")
    public Result<Post> updatePost(@PathVariable Long id, @RequestBody Post post, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        if (userId == null) {
            return Result.error("Please login first");
        }
        post.setId(id);
        post.setAuthorId(userId);
        Post updated = postService.updatePost(post);
        if (updated != null) {
            return Result.success("Post updated successfully", updated);
        }
        return Result.error("Update failed or no permission");
    }

    @DeleteMapping("/{id}")
    public Result<Void> deletePost(@PathVariable Long id, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        if (userId == null) {
            return Result.error("Please login first");
        }
        if (postService.deletePost(id, userId)) {
            return Result.success("Post deleted successfully", null);
        }
        return Result.error("Delete failed or no permission");
    }

    @GetMapping("/my")
    public Result<List<Post>> getMyPosts(HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        if (userId == null) {
            return Result.error("Please login first");
        }
        List<Post> posts = postService.getMyPosts(userId);
        return Result.success(posts);
    }

    private Long getCurrentUserId(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.getUsernameFromToken(token);
                User user = userService.getUserByUsername(username);
                return user != null ? user.getId() : null;
            }
        }
        return null;
    }
}
