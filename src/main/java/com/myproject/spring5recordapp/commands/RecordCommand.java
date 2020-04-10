package com.myproject.spring5recordapp.commands;

import com.myproject.spring5recordapp.domain.BuildingType;
import com.myproject.spring5recordapp.domain.Material;
import com.myproject.spring5recordapp.domain.Notes;
import com.myproject.spring5recordapp.domain.ProjectType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecordCommand {

    private Long id;
    private String jobName;
    private String dateToday;
    private String estimator;
    private String duration;
    private String dateStart;
    private String workScope;
    private String labor;
    private Set<MaterialCommand> materials = new HashSet<>();
    private BuildingType buildingType;
    private NotesCommand notes;
    private Set<ProjectTypeCommand> projectTypes = new HashSet<>();
}
