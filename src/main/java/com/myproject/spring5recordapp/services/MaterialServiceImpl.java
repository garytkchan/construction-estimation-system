package com.myproject.spring5recordapp.services;


import com.myproject.spring5recordapp.commands.MaterialCommand;
import com.myproject.spring5recordapp.converters.MaterialCommandToMaterial;
import com.myproject.spring5recordapp.converters.MaterialToMaterialCommand;
import com.myproject.spring5recordapp.domain.Material;
import com.myproject.spring5recordapp.domain.Record;
import com.myproject.spring5recordapp.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialToMaterialCommand materialToMaterialCommand;
    private final MaterialCommandToMaterial materialCommandToMaterial;
    private final RecordRepository recordRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public MaterialServiceImpl(MaterialToMaterialCommand materialToMaterialCommand, MaterialCommandToMaterial materialCommandToMaterial, RecordRepository recordRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.materialToMaterialCommand = materialToMaterialCommand;
        this.materialCommandToMaterial = materialCommandToMaterial;
        this.recordRepository = recordRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public MaterialCommand findByRecordIdAndMaterialId(Long recordId, Long materialId) {

        Optional<Record> recordOptional = recordRepository.findById(recordId);

        if (!recordOptional.isPresent()) {

            log.error("Record id not found. ID: " + recordId);
        }
        Record record = recordOptional.get();

        Optional<MaterialCommand> materialCommandOptional = record.getMaterials().stream()
                .filter(material -> material.getId().equals(materialId))
                .map(material -> materialToMaterialCommand.convert(material)).findFirst();

        if (!materialCommandOptional.isPresent()) {
            log.error("Material id not found: " + materialId);
        }
        return materialCommandOptional.get();
    }


    @Override
    @Transactional
    public MaterialCommand saveMaterialCommand(MaterialCommand command) {

        Optional<Record> recordOptional = recordRepository.findById(command.getRecordId());

        if (!recordOptional.isPresent()) {

            log.error("Record not found for id: " + command.getRecordId());
            return new MaterialCommand();
        }
        else {
            Record record = recordOptional.get();

            Optional<Material> materialOptional = record
                    .getMaterials()
                    .stream()
                    .filter(material -> material.getId().equals(command.getId()))
                    .findFirst();

            if (materialOptional.isPresent()) {
                Material materialFound = materialOptional.get();
                materialFound.setDescription((command.getDescription()));
                materialFound.setAmount(command.getAmount());
                materialFound.setUom(unitOfMeasureRepository
                                    .findById(command.getUom().getId())
                                    .orElseThrow(()-> new RuntimeException("UOM NOT FOUND")));
            }
            else {
                // add new materials
                Material material = materialCommandToMaterial.convert(command);
                material.setRecord(record);
                record.addMaterial(material);
            }
            Record savedRecord = recordRepository.save(record);

            Optional<Material> savedMaterialOptional = savedRecord.getMaterials().stream()
                    .filter(recordMaterials -> recordMaterials.getId().equals(command.getId()))
                    .findFirst();

            if (!savedMaterialOptional.isPresent()) {

                savedMaterialOptional = savedRecord.getMaterials().stream()
                        .filter(recordMaterials -> recordMaterials.getDescription().equals(command.getDescription()))
                        .filter(recordMaterials -> recordMaterials.getAmount().equals(command.getAmount()))
                        .filter(recordMaterials -> recordMaterials.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();
            }
            return materialToMaterialCommand.convert(savedMaterialOptional.get());

        }
    }

    @Override
    public void deleteById(Long recordId, Long idToDelete) {

        log.debug("Deleting material: " + recordId + ":" + idToDelete);

        Optional<Record> recordOptional = recordRepository.findById(recordId);

        if (recordOptional.isPresent()) {
            Record record = recordOptional.get();
            log.debug("found record");

            Optional<Material> materialOptional = record
                    .getMaterials()
                    .stream()
                    .filter(material -> material.getId().equals(idToDelete))
                    .findFirst();

            if (materialOptional.isPresent()) {
                log.debug("Found Material");
                Material materialToDelete = materialOptional.get();
                materialToDelete.setRecord(null);
                record.getMaterials().remove(materialOptional.get());
                recordRepository.save(record);
            }
        }
        else {
            log.debug("Record ID Not Found. ID: " + recordId);
        }
    }
}
