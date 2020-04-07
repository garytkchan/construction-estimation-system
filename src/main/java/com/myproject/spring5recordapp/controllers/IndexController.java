package com.myproject.spring5recordapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})  // either blank, / or index would redirect to index page
    public String getIndexPage(){
        System.out.println("Some message to say....1234");
        return "index";
    }

}
