package com.hanson.freemarker.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @date 2024/10/23 14:46
 **/
@Data
@ToString
public class Student {
    private String name;//姓名
    private int age;//年龄
    private Date birthday;//生日
    private Float wallet;//钱包
    private List<Student> friends;//朋友列表
    private Student bestFriend;//最好的朋友
}