package com.myproject.spring5recordapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long recordId, MultipartFile file);
}
