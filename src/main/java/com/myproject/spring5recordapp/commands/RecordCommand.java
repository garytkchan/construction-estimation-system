package com.myproject.spring5recordapp.commands;

import com.myproject.spring5recordapp.domain.BuildingType;
import com.myproject.spring5recordapp.domain.Material;
import com.myproject.spring5recordapp.domain.Notes;
import com.myproject.spring5recordapp.domain.ProjectType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecordCommand {

    private Long id;

    @Size(min = 3, max = 255)
    private String jobName;

    @Size(min = 3, max = 255)
    private String dateToday;

    @Size(min = 3, max = 255)
    private String estimator;

    @Size(min = 3, max = 255)
    private String duration;

    @Size(min = 3, max = 255)
    private String dateStart;

    @Size(min = 3, max = 255)
    private String workScope;

    @Size(min = 3, max = 255)
    private String labor;

    private Set<MaterialCommand> materials = new HashSet<>();
    private BuildingType buildingType;
    private NotesCommand notes;
    private Set<ProjectTypeCommand> projectTypes = new HashSet<>();
    private Byte[] image;
}
