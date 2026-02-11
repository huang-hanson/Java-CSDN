package com.blog.service;

import com.blog.entity.User;

public interface UserService {
    User login(String username, String password);
    User register(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    boolean updateUser(User user);
}
