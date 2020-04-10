package com.myproject.spring5recordapp.services;

import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.converters.RecordCommandToRecord;
import com.myproject.spring5recordapp.converters.RecordToRecordCommand;
import com.myproject.spring5recordapp.domain.Record;
import com.myproject.spring5recordapp.repositories.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    private final RecordCommandToRecord recordCommandToRecord;
    private final RecordToRecordCommand recordToRecordCommand;


    public RecordServiceImpl(RecordRepository recordRepository, RecordCommandToRecord recordCommandToRecord, RecordToRecordCommand recordToRecordCommand) {
        this.recordRepository = recordRepository;
        this.recordCommandToRecord = recordCommandToRecord;
        this.recordToRecordCommand = recordToRecordCommand;
    }

    @Override
    public Set<Record> getRecords() {

        log.debug("This is the service");
        Set<Record> recordSet = new HashSet<>();

        recordRepository.findAll().iterator().forEachRemaining(recordSet::add);

        return recordSet;
    }

    @Override
    public Record findById(long l) {

        Optional<Record> recordOptional = recordRepository.findById(l);
        if (!recordOptional.isPresent()) {
            throw new RuntimeException("Record not found");
        }
        return recordOptional.get();
    }


    @Override
    @Transactional
    public RecordCommand saveRecordCommand(RecordCommand command) {

        Record detachedRecord = recordCommandToRecord.convert(command);

        Record savedRecord = recordRepository.save(detachedRecord);
        log.debug("Saved Record ID: " + savedRecord.getId());
        return  recordToRecordCommand.convert(savedRecord);

    }
}
