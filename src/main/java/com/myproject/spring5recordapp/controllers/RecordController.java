package com.myproject.spring5recordapp.controllers;

import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.services.RecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping("/record/show/{id}")
    public String showById(@PathVariable String id, Model model) {

        model.addAttribute("record", recordService.findById((new Long(id))));
        return "record/show";
    }

    @RequestMapping("/record/new")
    public String newRecord(Model model) {

        model.addAttribute("record", new RecordCommand());
        return "record/recordform";
    }

    @PostMapping
    @RequestMapping("record")
    public String saveOrUpdate(@ModelAttribute RecordCommand command) {

        RecordCommand savedCommand = recordService.saveRecordCommand(command);
        return  "redirect:/record/show/" + savedCommand.getId();
    }
}
