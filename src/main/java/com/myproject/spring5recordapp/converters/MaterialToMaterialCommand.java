package com.myproject.spring5recordapp.converters;

import lombok.Synchronized;
import com.myproject.spring5recordapp.commands.MaterialCommand;
import com.myproject.spring5recordapp.domain.Material;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MaterialToMaterialCommand implements  Converter<Material, MaterialCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public MaterialToMaterialCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public MaterialCommand convert(Material source) {
        if (source == null) {
            return null;
        }
        MaterialCommand materialCommand = new MaterialCommand();
        materialCommand.setId((source.getId()));
        materialCommand.setAmount(source.getAmount());
        materialCommand.setDescription(source.getDescription());
        materialCommand.setUnitOfMeasure(uomConverter.convert(source.getUom()));
        return materialCommand;
    }
}
