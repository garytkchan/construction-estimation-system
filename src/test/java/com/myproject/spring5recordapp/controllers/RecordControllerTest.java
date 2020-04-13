package com.myproject.spring5recordapp.controllers;

import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.domain.Record;
import com.myproject.spring5recordapp.services.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.mockito.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecordControllerTest {

    @Mock
    RecordService recordService;

    RecordController controller;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);

        controller = new RecordController(recordService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    // Unit Test: Get Record ID and redirect to URL /record/show
    @Test
    public void testGetRecord() throws Exception {

        Record record = new Record();
        record.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(recordService.findById(anyLong())).thenReturn(record);

        mockMvc.perform(get("/record/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("record/show"))
                .andExpect(model().attributeExists("record"));
    }

    @Test
    public void testGetNewRecordForm() throws Exception {

        RecordCommand command = new RecordCommand();

        mockMvc.perform(get("/record/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("record/recordform"))
                .andExpect(model().attributeExists("record"));

    }

    @Test
    public void testPostNewRecordForm() throws Exception {

        RecordCommand command = new RecordCommand();
        command.setId(4L);

        when(recordService.saveRecordCommand(any())).thenReturn(command);

        mockMvc.perform(post("/record")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("jobName", "some String")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/record/4/show"));
                // updated URL --------------------------------------------------
    }

    @Test
    public void testGetUpdateView() throws Exception {

        RecordCommand command = new RecordCommand();
        command.setId(4L);

        when(recordService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/record/4/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("record/recordform"))
                .andExpect(model().attributeExists("record"));

    }
}