package com.myproject.spring5recordapp.domain;

import javax.persistence.*;

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Record record;

    @Lob
    private String recordNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public String getRecordNotes() {
        return recordNotes;
    }

    public void setRecordNotes(String recordNotes) {
        this.recordNotes = recordNotes;
    }


}
