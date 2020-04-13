package com.myproject.spring5recordapp.services;

import com.myproject.spring5recordapp.commands.MaterialCommand;

public interface MaterialService {

    MaterialCommand findByRecordIdAndMaterialId(Long recordId, Long materialId);

    MaterialCommand saveMaterialCommand(MaterialCommand command);

    void deleteById(Long recordId, Long idToDelete);
}
