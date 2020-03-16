package com.binge.lesiyu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }
}
