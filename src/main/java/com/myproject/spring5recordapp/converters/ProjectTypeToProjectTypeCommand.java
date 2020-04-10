package com.myproject.spring5recordapp.converters;

import com.myproject.spring5recordapp.commands.ProjectTypeCommand;
import com.myproject.spring5recordapp.domain.ProjectType;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ProjectTypeToProjectTypeCommand implements Converter<ProjectType, ProjectTypeCommand>{

    @Synchronized
    @Nullable
    @Override
    public ProjectTypeCommand convert(ProjectType source) {
        if (source == null) {
            return null;
        }
        final ProjectTypeCommand projectTypeCommand = new ProjectTypeCommand();

        projectTypeCommand.setId(source.getId());
        projectTypeCommand.setDescription(source.getDescription());
        return projectTypeCommand;
    }
}
