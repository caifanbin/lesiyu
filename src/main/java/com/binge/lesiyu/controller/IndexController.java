package com.binge.lesiyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "/about";
    }

    @RequestMapping("/friends")
    public String friends(){
        return "/friends";
    }

    @RequestMapping("/tracks")
    public String tracks(){
        return "/tracks";
    }

    @RequestMapping("/bokeindex")
    public String bokeindex(){
        return "/boke/index";
    }
}
