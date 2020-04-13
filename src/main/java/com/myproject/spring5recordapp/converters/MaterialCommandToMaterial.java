package com.myproject.spring5recordapp.converters;

import com.myproject.spring5recordapp.commands.MaterialCommand;
import com.myproject.spring5recordapp.domain.Material;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MaterialCommandToMaterial implements Converter<MaterialCommand, Material> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public MaterialCommandToMaterial(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    public Material convert(MaterialCommand source) {

        if (source == null) {
            return null;
        }
        final Material material = new Material();
        material.setId(source.getId());
        material.setAmount((source.getAmount()));
        material.setDescription(source.getDescription());
        material.setUom(uomConverter.convert(source.getUom()));
        return material;
    }
}
