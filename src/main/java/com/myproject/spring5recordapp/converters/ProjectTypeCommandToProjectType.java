package com.myproject.spring5recordapp.converters;

import com.myproject.spring5recordapp.commands.ProjectTypeCommand;
import com.myproject.spring5recordapp.domain.ProjectType;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ProjectTypeCommandToProjectType implements Converter<ProjectTypeCommand, ProjectType>{

    @Synchronized
    @Nullable
    @Override
    public ProjectType convert(ProjectTypeCommand source) {

        if (source == null) {
            return null;
        }
        final ProjectType projectType = new ProjectType();
        projectType.setId(source.getId());
        projectType.setDescription(source.getDescription());

        return projectType;
    }
}
