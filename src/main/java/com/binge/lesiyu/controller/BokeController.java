package com.binge.lesiyu.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BokeController {

    @RequestMapping("/editor")
    public String editor(){
        return "boke/editor";
    }

    @RequestMapping("/release")
    public String release(){
        return "boke/release";
    }
}
