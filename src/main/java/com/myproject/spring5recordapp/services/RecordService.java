package com.myproject.spring5recordapp.services;

import com.myproject.spring5recordapp.domain.Record;

import java.util.Set;

public interface RecordService {

    Set<Record> getRecords();
}
