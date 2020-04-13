package com.myproject.spring5recordapp.services;

import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.domain.Record;

import java.util.Set;

public interface RecordService {

    Set<Record> getRecords();

    Record findById(Long l);

    RecordCommand saveRecordCommand(RecordCommand command);

    RecordCommand findCommandById(Long l);

    void deleteById(Long idToDelete);

}
