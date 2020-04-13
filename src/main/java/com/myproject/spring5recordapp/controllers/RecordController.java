package com.myproject.spring5recordapp.controllers;

import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.exceptions.NotFoundException;
import com.myproject.spring5recordapp.services.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecordController {

    private static final String RECORD_RECORDFORM_URL = "record/recordform";
    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/record/{id}/show")
    public String showById(@PathVariable String id, Model model) {

        model.addAttribute("record", recordService.findById(Long.valueOf(id)));
        return "record/show";
    }


    @GetMapping("/record/new")
    public String newRecord(Model model) {

        model.addAttribute("record", new RecordCommand());
        return RECORD_RECORDFORM_URL;
    }


    @GetMapping("record/{id}/update")
    public String updateRecord(@PathVariable String id, Model model) {

        model.addAttribute("record", recordService.findCommandById(Long.valueOf(id)));
        return RECORD_RECORDFORM_URL;
    }


    @PostMapping("record")
    public String saveOrUpdate(@ModelAttribute("record") RecordCommand command) {

        RecordCommand savedCommand = recordService.saveRecordCommand(command);
        return  "redirect:/record/" + savedCommand.getId() + "/show";
    }


    @GetMapping("record/{id}/delete")
    public String deleteById(@PathVariable String id) {

        log.debug("Deleting id: " + id);

        recordService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {

        // Get Exception message from Record Service

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        // match to 404notfound.html
        modelAndView.setViewName("404error");

        modelAndView.addObject("exception", exception);
        return modelAndView;
    }


}
