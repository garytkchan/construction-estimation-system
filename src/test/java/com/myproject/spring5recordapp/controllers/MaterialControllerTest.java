package com.myproject.spring5recordapp.controllers;

import com.myproject.spring5recordapp.commands.MaterialCommand;
import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.domain.UnitOfMeasure;
import com.myproject.spring5recordapp.services.MaterialService;
import com.myproject.spring5recordapp.services.RecordService;
import com.myproject.spring5recordapp.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;


class MaterialControllerTest {

    @Mock
    RecordService recordService;

    @Mock
    MaterialService materialService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    MaterialController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        controller = new MaterialController(recordService, materialService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListMaterials() throws Exception {

        //given
        RecordCommand recordCommand = new RecordCommand();
        when(recordService.findCommandById(anyLong())).thenReturn(recordCommand);

        //when
        mockMvc.perform(get("/record/1/materials"))
                .andExpect(status().isOk())
                .andExpect(view().name("record/material/list"))
                .andExpect(model().attributeExists("record"));

        //then
        verify(recordService, times(1)).findCommandById(anyLong());

    }

    @Test
    public void testShowIngredient() throws Exception {
        //given
        MaterialCommand materialCommand = new MaterialCommand();

        //when
        when(materialService.findByRecordIdAndMaterialId(anyLong(), anyLong())).thenReturn(materialCommand);

        //then
        mockMvc.perform(get("/record/1/material/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("record/material/show"))
                .andExpect(model().attributeExists("material"));
    }

    @Test
    public void testUpdateMaterialForm() throws Exception {
        //given
        MaterialCommand materialCommand = new MaterialCommand();

        //when
        when(materialService.findByRecordIdAndMaterialId(anyLong(), anyLong())).thenReturn(materialCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(get("/record/1/material/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("record/material/materialform"))
                .andExpect(model().attributeExists("material"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        //given
        MaterialCommand command = new MaterialCommand();
        command.setId(3L);
        command.setRecordId(2L);

        //when
        when(materialService.saveMaterialCommand(any())).thenReturn(command);

        //then
        mockMvc.perform(post("/record/2/material")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/record/2/material/3/show"));

    }
}