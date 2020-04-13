package com.myproject.spring5recordapp.services;
import com.myproject.spring5recordapp.repositories.RecordRepository;
import com.myproject.spring5recordapp.domain.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecordRepository recordRepository;

    public ImageServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recordId, MultipartFile file) {

        try {
            Record record = recordRepository.findById(recordId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            record.setImage(byteObjects);
            recordRepository.save(record);

        }
        catch (IOException e) {
            log.error("ERROR occurred: ", e);
            e.printStackTrace();
        }

    }
}
