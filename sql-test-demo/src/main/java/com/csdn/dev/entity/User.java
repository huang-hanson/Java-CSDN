package com.csdn.dev.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName User
 * @date 2024/12/25 19:30
 **/
@Data
@TableName("users")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String name;

    @TableField("password")
    private String password;

    @TableField("age")
    private String age;
}