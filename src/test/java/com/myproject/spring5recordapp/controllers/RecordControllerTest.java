package com.myproject.spring5recordapp.controllers;

import com.myproject.spring5recordapp.domain.Record;
import com.myproject.spring5recordapp.services.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.mockito.*;

import java.util.HashSet;
import java.util.Set;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecordControllerTest {

    @Mock
    RecordService recordService;

    RecordController controller;

    @BeforeEach
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);

        controller = new RecordController(recordService);
    }

    // Unit Test: Get Record ID and redirect to URL /record/show
    @Test
    public void testGetRecord() throws Exception {

        Record record = new Record();
        record.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(recordService.findById(anyLong())).thenReturn(record);

        mockMvc.perform(get("/record/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("record/show"))
                .andExpect(model().attributeExists("record"));
    }
}