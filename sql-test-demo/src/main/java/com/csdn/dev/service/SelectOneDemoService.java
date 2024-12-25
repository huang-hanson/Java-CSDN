package com.csdn.dev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csdn.dev.entity.User;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SelectOneDemoService
 * @date 2024/12/25 19:40
 **/
public interface SelectOneDemoService extends IService<User> {

    User selectOne(User user);
}