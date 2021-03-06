package com.binge.lesiyu.controller;

import com.binge.lesiyu.bean.User;
import com.binge.lesiyu.commons.AjaxResult;
import com.binge.lesiyu.service.UserService;
import com.binge.lesiyu.service.UserTextService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserTextService userTextService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/friends")
    public String friends(){
        return "friends";
    }

    @RequestMapping("/tracks")
    public String tracks(){
        return "tracks";
    }

    @RequestMapping("/bokeindex")
    public String bokeindex(Model model){
        model.addAttribute("alltext",userTextService.getall());
        return "boke/index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/registered")
    public String registered(){
        return "registered";
    }

    @ResponseBody
    @RequestMapping("/toregistered")
    public AjaxResult registered(User user,String authcode){

        return userService.registered(user,authcode);
    }

    @ResponseBody
    @RequestMapping("/authcode")
    public AjaxResult authcode(@Param("username") String username,@Param("email") String email){
        System.out.println("mailcon");
        return userService.mailSender(username,email);
    }




}
