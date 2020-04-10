package com.myproject.spring5recordapp.domain;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectTypeTest {

    ProjectType projectType;

    @BeforeEach
    public void setUp() {
        projectType = new ProjectType();
    }

    @Test
    void getId() {

        Long idValue = 4L;

        projectType.setId(idValue);
        assertEquals(idValue, projectType.getId());

    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecords() {
    }
}