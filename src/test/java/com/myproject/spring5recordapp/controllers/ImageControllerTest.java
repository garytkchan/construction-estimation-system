package com.myproject.spring5recordapp.controllers;

import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.services.ImageService;
import com.myproject.spring5recordapp.services.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecordService recordService;

    ImageController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        controller = new ImageController(imageService, recordService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getImageFrom() throws Exception {

        // Given
        RecordCommand command = new RecordCommand();
        command.setId(1L);

        when(recordService.findCommandById(anyLong())).thenReturn(command);

        // When
        mockMvc.perform(get("/record/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("record"));

        verify(recordService, times(1)).findCommandById(anyLong());
    }

    /*
    public void handleImagePost() throws Exception {

        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "Spring Framework Guru".getBytes());

        mockMvc.perform(multipart("/record/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/record/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }
    */

    @Test
    public void renderImageFromDB() throws Exception {

        // Given
        RecordCommand command = new RecordCommand();
        command.setId(1L);

        String s = "fake image text";
        Byte [] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;
        for (byte primByte : s.getBytes()) {
            bytesBoxed[i++] = primByte;
        }
        command.setImage(bytesBoxed);

        when(recordService.findCommandById(anyLong())).thenReturn(command);

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/record/1/recordimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();
        assertEquals(s.getBytes().length, responseBytes.length);
    }



}