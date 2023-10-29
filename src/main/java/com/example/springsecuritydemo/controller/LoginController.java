package com.example.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

//    @RequestMapping("/login")
//    public String login(){
//        return "redirect:main.html";
//    }

    @RequestMapping("/toMain")
    public String toMain(){
        return "redirect:main.html";
    }

    @RequestMapping("/toFail")
    public String toFail(){
        return "redirect:fail.html";
    }
}
