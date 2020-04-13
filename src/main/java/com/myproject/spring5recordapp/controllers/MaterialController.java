package com.myproject.spring5recordapp.controllers;

import com.myproject.spring5recordapp.commands.MaterialCommand;
import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.commands.UnitOfMeasureCommand;
import com.myproject.spring5recordapp.services.RecordService;
import com.myproject.spring5recordapp.services.MaterialService;
import com.myproject.spring5recordapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MaterialController {

    private final RecordService recordService;
    private final MaterialService materialService;
    private final UnitOfMeasureService unitOfMeasureService;


    public MaterialController(RecordService recordService, MaterialService materialService, UnitOfMeasureService unitOfMeasureService) {
        this.recordService = recordService;
        this.materialService = materialService;
        this.unitOfMeasureService = unitOfMeasureService;
    }


    @GetMapping("/record/{recordId}/materials")
    public String listMaterials(@PathVariable String recordId, Model model) {

        log.debug("Getting material list for record id: " + recordId);

        model.addAttribute("record", recordService.findCommandById(Long.valueOf(recordId)));
        return "record/material/list";
    }


    @GetMapping("record/{recordId}/material/{id}/show")
    public String showRecordMaterial(@PathVariable String recordId,
                                     @PathVariable String id, Model model) {

        model.addAttribute("material", materialService.findByRecordIdAndMaterialId(Long.valueOf(recordId), Long.valueOf(id)));
        return "record/material/show";
    }


    @GetMapping("record/{recordId}/material/{id}/update")
    public String updateRecordMaterial(@PathVariable String recordId,
                                       @PathVariable String id, Model model) {

        model.addAttribute("material", materialService.findByRecordIdAndMaterialId(Long.valueOf(recordId), Long.valueOf(id)));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "record/material/materialform";
    }


    @GetMapping("record/{recordId}/material/new")
    public String newMaterials(@PathVariable String recordId, Model model) {

        RecordCommand recordCommand = recordService.findCommandById(Long.valueOf(recordId));

        MaterialCommand materialCommand = new MaterialCommand();
        materialCommand.setRecordId(Long.valueOf(recordId));
        model.addAttribute("material", materialCommand);

        materialCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "record/material/materialform";
    }

    @PostMapping("record/{recordId}/material")
    public String saveOrUpdate(@ModelAttribute MaterialCommand command) {

        MaterialCommand savedCommand = materialService.saveMaterialCommand(command);

        log.debug("Saved record id: " + savedCommand.getRecordId());
        log.debug("saved material id" + savedCommand.getId());

        return "redirect:/record/" + savedCommand.getRecordId() + "/material/" + savedCommand.getId() + "/show";
    }



    @GetMapping("record/{recordId}/material/{id}/delete")
    public String deleteMaterial(@PathVariable String recordId,
                                 @PathVariable String id) {

        log.debug("deleting material id" + id);
        materialService.deleteById(Long.valueOf(recordId), Long.valueOf(id));

        return "redirect:/record/" + recordId + "/materials";
    }

}
