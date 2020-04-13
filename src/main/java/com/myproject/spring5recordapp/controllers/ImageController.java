package com.myproject.spring5recordapp.controllers;

import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.services.ImageService;
import com.myproject.spring5recordapp.services.RecordService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecordService recordService;

    public ImageController(ImageService imageService, RecordService recordService) {
        this.imageService = imageService;
        this.recordService = recordService;
    }

    @GetMapping("record/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {

        model.addAttribute("record", recordService.findCommandById(Long.valueOf(id)));
        return "record/imageuploadform";
    }

    @PostMapping("record/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {

        imageService.saveImageFile(Long.valueOf(id), file);
        return "redirect:/record/" + id + "/show";
    }

    @GetMapping("record/{id}/recordimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {

        RecordCommand recordCommand = recordService.findCommandById(Long.valueOf(id));

        byte[] byteArray = new byte[recordCommand.getImage().length];

        int i = 0;
        for (Byte wrappedByte : recordCommand.getImage()) {
            byteArray[i++] = wrappedByte;
        }
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream());

    }
}
