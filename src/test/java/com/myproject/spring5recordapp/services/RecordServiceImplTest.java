package com.myproject.spring5recordapp.services;

import com.myproject.spring5recordapp.domain.Record;
import com.myproject.spring5recordapp.repositories.RecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/*
class RecordServiceImplTest {

    RecordServiceImpl recordService;

    @Mock
    RecordRepository recordRepository;



    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        recordService = new RecordServiceImpl(recordRepository);
    }

    @Test
    public void getRecordByIdTest() throws Exception {
        Record record = new Record();
        record.setId(1L);
        Optional<Record> recordOptional = Optional.of(record);

        when(recordRepository.findById(anyLong())).thenReturn(recordOptional);

        Record recordReturned = recordService.findById(1L);

        assertNotNull(recordReturned, "Null record returned");
        verify(recordRepository, times(1)).findById(anyLong());
        verify(recordRepository, never()).findAll();
    }

    @Test
    void getRecords() {

        Record record = new Record();
        HashSet recordsData = new HashSet();
        recordsData.add(record);

        when(recordRepository.findAll()).thenReturn(recordsData);

        Set<Record> records = recordService.getRecords();
        assertEquals(records.size(), 1);
        verify(recordRepository, times(1)).findAll();
    }
}
*/
