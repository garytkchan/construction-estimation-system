package com.myproject.spring5recordapp.repositories;

import com.myproject.spring5recordapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
