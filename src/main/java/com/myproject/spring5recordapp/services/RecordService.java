package com.myproject.spring5recordapp.services;

import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.domain.Record;

import java.util.Set;

public interface RecordService {

    Set<Record> getRecords();
    Record findById(long l);
    RecordCommand saveRecordCommand(RecordCommand command);
}
