package com.myproject.spring5recordapp.repositories;

import com.myproject.spring5recordapp.domain.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record, Long> {
}
