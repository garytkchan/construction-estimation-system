package com.myproject.spring5recordapp.repositories;

import com.myproject.spring5recordapp.domain.ProjectType;
import com.myproject.spring5recordapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
