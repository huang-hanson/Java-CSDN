package com.design.patterns.behavioural.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName PersonStructure
 * @Description 构造对象
 * @date 2025/3/26 14:13
 **/
public class PersonStructure {

    private List<Person> personList = new ArrayList<>();

    public PersonStructure() {
        personList.add(new Student("张三", 20, 70));
        personList.add(new Student("李四", 21, 80));
        personList.add(new Student("王五", 22, 90));
        personList.add(new Teacher("李老师", 26, 3));
        personList.add(new Teacher("陈老师", 27, 4));
        personList.add(new Teacher("刘老师", 28, 5));
    }

    public void Accept(Visitor visitor) {
        for (Person person : personList) {
            person.Accept(visitor);
        }
    }
}