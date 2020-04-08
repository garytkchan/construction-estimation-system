package com.myproject.spring5recordapp.repositories;

import com.myproject.spring5recordapp.domain.ProjectType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectTypeRepository extends CrudRepository<ProjectType, Long> {

    Optional<ProjectType> findByDescription(String description);
}
