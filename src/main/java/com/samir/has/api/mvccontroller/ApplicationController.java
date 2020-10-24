package com.samir.has.api.mvccontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

    @RequestMapping("/")
    public String gotoHomePage(){
        return "index";
    }
}
