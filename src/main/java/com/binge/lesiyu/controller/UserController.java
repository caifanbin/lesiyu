package com.binge.lesiyu.controller;


import com.binge.lesiyu.bean.User;
import com.binge.lesiyu.commons.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/user")
public class UserController {

    @ResponseBody
    @RequestMapping("/gologin")
    public AjaxResult gologin(String username, String password){
        System.out.println("123");
        AjaxResult result = new AjaxResult();
        result.setSuccess(true);
        return result;
    }
}
