package com.myproject.spring5recordapp.controllers;

import com.myproject.spring5recordapp.services.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecordService recordService;

    public IndexController(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping({"", "/", "/index"})  // either blank, / or index would redirect to index page
    public String getIndexPage(Model model){

        log.debug("Getting Index page");

        model.addAttribute("records", recordService.getRecords());

        return "index";
    }

}
