package com.myproject.spring5recordapp.converters;

import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.domain.Record;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecordToRecordCommand implements Converter<Record, RecordCommand> {

    private final ProjectTypeToProjectTypeCommand projectTypeConverter;
    private final MaterialToMaterialCommand materialConverter;
    private final NotesToNotesCommand notesConverter;

    public RecordToRecordCommand(ProjectTypeToProjectTypeCommand projectTypeConverter, MaterialToMaterialCommand materialConverter, NotesToNotesCommand notesConverter) {
        this.projectTypeConverter = projectTypeConverter;
        this.materialConverter = materialConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecordCommand convert(Record source) {
        if (source == null) {
            return null;
        }
        final RecordCommand command = new RecordCommand();
        command.setId(source.getId());
        command.setLabor(source.getLabor());
        command.setWorkScope(source.getWorkScope());
        command.setEstimator(source.getEstimator());
        command.setDuration(source.getDuration());
        command.setDateStart(source.getDateStart());
        command.setDateToday(source.getDateToday());
        command.setJobName(source.getJobName());
        command.setBuildingType(source.getBuildingType());
        // Notes Object
        command.setNotes(notesConverter.convert(source.getNotes()));

        // ProjectType Object
        if (source.getProjectTypes() != null && source.getProjectTypes().size() > 0) {
            source.getProjectTypes()
                    .forEach(projectType -> command.getProjectTypes().add(projectTypeConverter.convert(projectType)));
        }

        // Material Object
        if (source.getMaterials() != null && source.getMaterials().size() > 0) {
            source.getMaterials()
                    .forEach(material -> command.getMaterials().add(materialConverter.convert(material)));
        }
        return command;
    }
}
