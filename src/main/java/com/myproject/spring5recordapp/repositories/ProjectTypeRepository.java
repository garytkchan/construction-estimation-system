package com.myproject.spring5recordapp.repositories;

import com.myproject.spring5recordapp.domain.ProjectType;
import org.springframework.data.repository.CrudRepository;

public interface ProjectTypeRepository extends CrudRepository<ProjectType, Long> {
}
