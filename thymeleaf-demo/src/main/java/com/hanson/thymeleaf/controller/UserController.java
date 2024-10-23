package com.hanson.thymeleaf.controller;

import com.hanson.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @date 2024/10/23 18:44
 **/
@Controller
public class UserController {

    @RequestMapping(value = "/message")
    public String message(Model model) {
        model.addAttribute("data","SpringBoot框架集成Thymeleaf模板引擎");
        return "message";
    }

    @GetMapping("/hello1")
    public String hello1(Model model, HttpSession session) {
        User user = new User();
        user.setId(11);
        user.setAge(18);
        user.setName("赵子龙");
        model.addAttribute("user",user);
        return "hello1";
    }

    @GetMapping("/hello2")
    public String hello2(Model model, HttpSession session) {
        User user = new User();
        user.setId(11);
        user.setAge(18);
        user.setName("赵子龙");
        model.addAttribute("user",user);
        return "hello2";
    }

    @GetMapping("/hello3")
    public String hello3(Model model, HttpSession session) {
        User user = new User();
        user.setId(11);
        user.setAge(18);
        user.setName("赵子龙");
        model.addAttribute("user",user);
        return "hello3";
    }

    @GetMapping("/hello4")
    public String hello4(Model model, HttpSession session) {
        return "hello4";
    }

    @GetMapping("/hello5")
    public String hello5(Model model, HttpSession session) {
        List<User> list = new ArrayList<>();
        for (int i = 0 ; i < 5; i++) {
            User user = new User();
            user.setId(i);
            user.setName("黄汉升"+i);
            user.setAge(18);
            list.add(user);
        }
        model.addAttribute("users",list);
        return "hello5";
    }

    @GetMapping("/hello6")
    public String hello6(Model model, HttpSession session) {
        return "hello6";
    }

    @GetMapping("/hello7")
    public String hello7(Model model, HttpSession session) {
        return "hello7";
    }

    @GetMapping("/hello8")
    public String hello8(Model model, HttpSession session) {
        User user = new User();
        user.setId(11);
        user.setAge(18);
        user.setName("赵子龙");
        model.addAttribute("user",user);
        return "hello8";
    }

    @GetMapping("/hello9")
    public String hello9(Model model, HttpSession session) {
        return "hello9";
    }

    @GetMapping("/hello10")
    public String hello10(Model model, HttpSession session) {
        return "hello10";
    }

    @GetMapping("/hello11")
    public String hello11(Model model, HttpSession session) {
        return "hello11";
    }

    @GetMapping("/hello12")
    public String hello12(Model model, HttpSession session) {
        List<User> list = new ArrayList<>();
        for (int i = 0 ; i < 5; i++) {
            User user = new User();
            user.setId(i);
            user.setName("黄汉升"+i);
            user.setAge(18);
            list.add(user);
        }
        model.addAttribute("users",list);
        return "hello12";
    }

    @GetMapping("/hello13")
    public String hello13(Model model, HttpSession session) {
        session.setAttribute("name","Hanson");
        List<User> list = new ArrayList<>();
        for (int i = 0 ; i < 5; i++) {
            User user = new User();
            user.setId(i);
            user.setName("黄汉升"+i);
            user.setAge(18);
            list.add(user);
        }
        model.addAttribute("users",list);
        return "hello13";
    }

    @GetMapping("/hello14")
    public String hello14(Model model, HttpSession session) {
        User user = new User();
        user.setId(11);
        user.setAge(18);
        user.setName("赵子龙");
        model.addAttribute("user",user);
        return "hello14";
    }
}