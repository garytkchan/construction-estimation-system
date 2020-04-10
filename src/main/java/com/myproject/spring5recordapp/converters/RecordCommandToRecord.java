package com.myproject.spring5recordapp.converters;

import com.myproject.spring5recordapp.commands.RecordCommand;
import com.myproject.spring5recordapp.domain.Record;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecordCommandToRecord implements Converter<RecordCommand, Record> {

    private final ProjectTypeCommandToProjectType projectTypeConverter;
    private final MaterialCommandToMaterial materialConverter;
    private final NotesCommandToNotes notesConverter;

    public RecordCommandToRecord(ProjectTypeCommandToProjectType projectTypeConverter, MaterialCommandToMaterial materialConverter, NotesCommandToNotes notesConverter) {
        this.projectTypeConverter = projectTypeConverter;
        this.materialConverter = materialConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Record convert(RecordCommand source) {
        if (source == null) {
            return null;
        }
        final Record record = new Record();
        record.setId(source.getId());
        record.setLabor(source.getLabor());
        record.setWorkScope(source.getWorkScope());
        record.setEstimator(source.getEstimator());
        record.setDuration(source.getDuration());
        record.setDateStart(source.getDateStart());
        record.setDateToday(source.getDateToday());
        record.setJobName(source.getJobName());
        record.setBuildingType(source.getBuildingType());
        // Notes Object
        record.setNotes(notesConverter.convert(source.getNotes()));

        // ProjectType Object
        if (source.getProjectTypes() != null && source.getProjectTypes().size() > 0) {
            source.getProjectTypes()
                    .forEach(projectType -> record.getProjectTypes().add(projectTypeConverter.convert(projectType)));
        }

        // Material Object
        if (source.getMaterials() != null && source.getMaterials().size() > 0) {
            source.getMaterials()
                    .forEach(material -> record.getMaterials().add(materialConverter.convert(material)));
        }
        return record;
    }
}
