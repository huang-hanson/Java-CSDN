package com.hanson.java.entity;

import lombok.Data;

import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Person
 * @date 2024/12/12 19:20
 **/
@Data
public class Person {
    private int id;
    private String name; // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area; // 地区
    private List<Person> employeeList; //下属

    public Person() {
    }

    // 构造方法
    public Person(String name, int salary, int age,String sex,String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    // 构造方法
    public Person(int id, String name, int salary, int age, String sex, String area) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}