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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    @Mock
    RecordService recordService;

    @Mock
    Model model;

    IndexController controller;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        controller = new IndexController((recordService));
    }

    @Test
    void testMockMVC() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getIndexPage() {

        // Given
        Set<Record> records = new HashSet<>();
        records.add(new Record());

        Record record = new Record();
        record.setId(1L);

        records.add(record);

        when(recordService.getRecords()).thenReturn(records);

        ArgumentCaptor<Set<Record>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // When
        String viewName = controller.getIndexPage(model);

        // Then
        assertEquals("index", viewName);
        verify(recordService, times(1)).getRecords();
        verify(model, times(1)).addAttribute(eq("records"), argumentCaptor.capture());
        Set<Record> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}