package com.hanson.freemarker.controller;

import com.hanson.freemarker.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author hanson.huang
 * @version V1.0
 * @date 2024/10/23 14:50
 **/
@RequestMapping("/freemarker")
@Controller  //这里不要使用@RestController，要输出html网页，RestController输出时json数据
public class FreemarkerController {

    //测试1
    @RequestMapping("/test1")
    public String test1(Map<String, Object> map) {
        //map就是freemarker模板所使用的数据
        map.put("name", "黄汉升");

        //返回freemarker模板的位置，基于resources/templates路径的
        return "test1";
    }

    @RequestMapping("/test2")
    public String test2(Map<String, Object> map) {
        //向数据模型放数据
        map.put("name", "黄汉升");
        Student stu1 = new Student();
        stu1.setName("赵子龙");
        stu1.setAge(21);
        stu1.setWallet(1314.2f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("关云长");
        stu2.setWallet(520.0f);
        stu2.setAge(20);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus", stus);
        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        //向数据模型放数据
        map.put("stu1", stu1);
        //向数据模型放数据
        map.put("stuMap", stuMap);
        //返回模板文件名称
        return "test2";
    }

    @RequestMapping("/test3")
    public String test3(Map<String, Object> map) {
        //向数据模型放数据
        map.put("name", "黄汉升");
        Student stu1 = new Student();
        stu1.setName("赵子龙");
        stu1.setAge(21);
        stu1.setWallet(1314.2f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("关云长");
        stu2.setWallet(520.0f);
        stu2.setAge(20);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus", stus);
        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        //向数据模型放数据
        map.put("stu1", stu1);
        //向数据模型放数据
        map.put("stuMap", stuMap);
        //返回模板文件名称
        return "test3";
    }

    @RequestMapping("/test4")
    public String test4(Map<String, Object> map) {
        //向数据模型放数据
        map.put("name", "黄汉升");
        Student stu1 = new Student();
        stu1.setName("赵子龙");
        stu1.setAge(21);
        stu1.setWallet(1314.2f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("关云长");
        stu2.setWallet(520.0f);
        stu2.setAge(20);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus", stus);
        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        //向数据模型放数据
        map.put("stu1", stu1);
        //向数据模型放数据
        map.put("stuMap", stuMap);
        //返回模板文件名称
        return "test4";
    }

    @RequestMapping("/test5")
    public String test5(Map<String, Object> map) {
        //向数据模型放数据
        map.put("name", "黄汉升");
        Student stu1 = new Student();
        stu1.setName("赵子龙");
        stu1.setAge(21);
        stu1.setWallet(1314.2f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("关云长");
        stu2.setWallet(520.0f);
        stu2.setAge(20);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
//        map.put("stus",stus);
        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        //向数据模型放数据
        map.put("stu1", stu1);
        //向数据模型放数据
        map.put("stuMap", stuMap);
        //返回模板文件名称
        return "test5";
    }

    @RequestMapping("/test6")
    public String test6(Map<String, Object> map) {
        //向数据模型放数据
        map.put("name", "黄汉升");
        Student stu1 = new Student();
        stu1.setName("赵子龙");
        stu1.setAge(21);
        stu1.setWallet(1314.2f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("关云长");
        stu2.setWallet(520.0f);
        stu2.setAge(20);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus", stus);
        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
//        stuMap.put("stu1",stu1);
        stuMap.put("stu2", stu2);
        //向数据模型放数据
//        map.put("stu1",stu1);
        //向数据模型放数据
        map.put("stuMap", stuMap);
        //返回模板文件名称
        return "test6";
    }

    @RequestMapping("/test7")
    public String test7(Map<String, Object> map) {
        //向数据模型放数据
        map.put("name", "黄汉升");
        Student stu1 = new Student();
        stu1.setName("赵子龙");
        stu1.setAge(21);
        stu1.setWallet(1314.2f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("关云长");
        stu2.setWallet(520.0f);
        stu2.setAge(20);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus", stus);
        return "test7";
    }

    @RequestMapping("/test8")
    public String test8(Map<String, Object> map) {
        //向数据模型放数据
        map.put("name", "黄汉升");
        Student stu1 = new Student();
        stu1.setName("赵子龙");
        stu1.setAge(21);
        stu1.setWallet(1314.2f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("关云长");
        stu2.setWallet(520.0f);
        stu2.setAge(20);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus", stus);
        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        //向数据模型放数据
        map.put("stu1", stu1);
        //向数据模型放数据
        map.put("stuMap", stuMap);
        //返回模板文件名称
        return "test8";
    }

    @RequestMapping("/test9")
    public String test9(Map<String, Object> map) {
        map.put("point", 102920122);
        return "test9";
    }

    @RequestMapping("/test10")
    public String test10(Map<String, Object> map) {
        map.put("point", 102920122);
        return "test10";
    }
}