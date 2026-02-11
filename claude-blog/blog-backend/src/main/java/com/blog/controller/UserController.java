package com.blog.controller;

import com.blog.common.Result;
import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            String token = jwtUtil.generateToken(loginUser.getUsername());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            loginUser.setPassword(null);
            data.put("user", loginUser);
            return Result.success("Login successful", data);
        }
        return Result.error("Invalid username or password");
    }

    @PostMapping("/register")
    public Result<Map<String, Object>> register(@RequestBody User user) {
        User newUser = userService.register(user);
        if (newUser != null) {
            String token = jwtUtil.generateToken(newUser.getUsername());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            newUser.setPassword(null);
            data.put("user", newUser);
            return Result.success("Registration successful", data);
        }
        return Result.error("Username already exists");
    }

    @GetMapping("/info/{id}")
    public Result<User> getUserInfo(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            user.setPassword(null);
            return Result.success(user);
        }
        return Result.error("User not found");
    }

    @PutMapping("/update")
    public Result<User> updateUser(@RequestBody User user) {
        User existUser = userService.getUserById(user.getId());
        if (existUser != null) {
            existUser.setNickname(user.getNickname());
            existUser.setEmail(user.getEmail());
            existUser.setAvatar(user.getAvatar());
            if (userService.updateUser(existUser)) {
                existUser.setPassword(null);
                return Result.success("Update successful", existUser);
            }
        }
        return Result.error("Update failed");
    }
}
