package com.myproject.spring5recordapp.services;

import com.myproject.spring5recordapp.domain.Record;
import com.myproject.spring5recordapp.repositories.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public Set<Record> getRecords() {

        log.debug("This is the service");
        Set<Record> recordSet = new HashSet<>();

        recordRepository.findAll().iterator().forEachRemaining(recordSet::add);

        return recordSet;
    }
}
