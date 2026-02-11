package com.blog.controller;

import com.blog.common.Result;
import com.blog.entity.Favorite;
import com.blog.entity.User;
import com.blog.service.FavoriteService;
import com.blog.service.UserService;
import com.blog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorite")
@CrossOrigin
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/{postId}")
    public Result<Void> addFavorite(@PathVariable Long postId, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        if (userId == null) {
            return Result.error("Please login first");
        }
        if (favoriteService.addFavorite(userId, postId)) {
            return Result.success("Favorite added successfully", null);
        }
        return Result.error("Already favorited");
    }

    @DeleteMapping("/{postId}")
    public Result<Void> removeFavorite(@PathVariable Long postId, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        if (userId == null) {
            return Result.error("Please login first");
        }
        if (favoriteService.removeFavorite(userId, postId)) {
            return Result.success("Favorite removed successfully", null);
        }
        return Result.error("Not favorited");
    }

    @GetMapping("/list")
    public Result<List<Favorite>> getMyFavorites(HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        if (userId == null) {
            return Result.error("Please login first");
        }
        List<Favorite> favorites = favoriteService.getMyFavorites(userId);
        return Result.success(favorites);
    }

    @GetMapping("/check/{postId}")
    public Result<Map<String, Boolean>> checkFavorite(@PathVariable Long postId, HttpServletRequest request) {
        Long userId = getCurrentUserId(request);
        Map<String, Boolean> data = new HashMap<>();
        data.put("favorited", favoriteService.isFavorited(userId, postId));
        return Result.success(data);
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
