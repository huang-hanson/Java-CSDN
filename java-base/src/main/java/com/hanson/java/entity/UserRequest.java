package com.hanson.java.entity;

import lombok.Data;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName UserRequest
 * @Description 用户请求参数
 * @date 2024/11/25 18:37
 **/
@Data
public class UserRequest {
    private String name;
    private Integer age;
    private String address;
    private String email;
    private String phone;
    private String imeiMd5;
}