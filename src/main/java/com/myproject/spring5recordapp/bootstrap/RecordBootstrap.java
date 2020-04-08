package com.myproject.spring5recordapp.bootstrap;

import com.myproject.spring5recordapp.domain.*;
import com.myproject.spring5recordapp.repositories.ProjectTypeRepository;
import com.myproject.spring5recordapp.repositories.RecordRepository;
import com.myproject.spring5recordapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecordBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ProjectTypeRepository projectTypeRepository;
    private final RecordRepository recordRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecordBootstrap(ProjectTypeRepository projectTypeRepository, RecordRepository recordRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.projectTypeRepository = projectTypeRepository;
        this.recordRepository = recordRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recordRepository.saveAll(getRecords());
        log.debug("Loading bootstrap data");
    }

    private List<Record> getRecords() {

        List<Record> records = new ArrayList<>();

        //'Set');('Piece')('Box');('Pack')('Roll');Bundle'); Other');

        Optional<UnitOfMeasure> setUomOptional = unitOfMeasureRepository.findByDescription("Set");
        if (!setUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pieceUomOptional = unitOfMeasureRepository.findByDescription("Piece");
        if (!pieceUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> boxUomOptional = unitOfMeasureRepository.findByDescription("Box");
        if (!boxUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> packUomOptional = unitOfMeasureRepository.findByDescription("Pack");
        if (!packUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> rollUomOptional = unitOfMeasureRepository.findByDescription("Roll");
        if (!rollUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> bundleUomOptional = unitOfMeasureRepository.findByDescription("Bundle");
        if (!bundleUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> otherUomOptional = unitOfMeasureRepository.findByDescription("Other");
        if (!otherUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        //get optionals
        UnitOfMeasure setUom = setUomOptional.get();
        UnitOfMeasure pieceUom = pieceUomOptional.get();
        UnitOfMeasure boxUom = boxUomOptional.get();
        UnitOfMeasure packUom = packUomOptional.get();
        UnitOfMeasure rollUom = rollUomOptional.get();
        UnitOfMeasure bundleUom = bundleUomOptional.get();
        UnitOfMeasure otherUom = otherUomOptional.get();

        //get project types
        Optional<ProjectType> newconstructionProjectTypeOptional = projectTypeRepository.findByDescription("New Construction");

        if (!newconstructionProjectTypeOptional.isPresent()) {
            throw new RuntimeException("Expected Project Type not found");
        }

        Optional<ProjectType> renovationProjectTypeOptional = projectTypeRepository.findByDescription("Renovation");

        if (!renovationProjectTypeOptional.isPresent()) {
            throw new RuntimeException("Expected Project Type not found");
        }

        Optional<ProjectType> demolitionProjectTypeOptional = projectTypeRepository.findByDescription("Demolition");

        if (!demolitionProjectTypeOptional.isPresent()) {
            throw new RuntimeException("Expected Project Type not found");
        }

        ProjectType newConstruction = newconstructionProjectTypeOptional.get();
        ProjectType renovation = renovationProjectTypeOptional.get();
        ProjectType demolition = demolitionProjectTypeOptional.get();

        // 2911 S Wallace
        Record sWallace = new Record();
        sWallace.setJobName("2911 S Wallace St., Chicago IL");
        sWallace.setDateToday("8/6/2019");
        sWallace.setDateStart("9/17/2019");
        sWallace.setDuration("14 Days");
        sWallace.setEstimator("John Rooney");
        sWallace.setWorkScope("Bathroom remodeling/plumbing rerouting");
        sWallace.setLabor("Plumbers: 2");
        sWallace.setBuildingType(BuildingType.HOUSE);

        Notes sWallaceNotes = new Notes();
        sWallaceNotes.setRecordNotes("All interior pipes are damaged. Required to repair all new copper pipes and " +
                "new plumbing fixtures");
        //----------------------------------------
        sWallaceNotes.setRecord(sWallace);
        sWallace.setNotes(sWallaceNotes);


        sWallace.addMaterial(new Material("2 inch copper pipes", new BigDecimal(34), bundleUom));
        sWallace.addMaterial(new Material("Drywall", new BigDecimal(12), pieceUom));
        sWallace.addMaterial(new Material("Sink", new BigDecimal(1), pieceUom));
        sWallace.addMaterial(new Material("floor tiles", new BigDecimal(4), packUom));
        sWallace.addMaterial(new Material("2 inch screws", new BigDecimal(10), boxUom));
        sWallace.addMaterial(new Material("toilet bowl", new BigDecimal(1), pieceUom));

        sWallace.getProjectTypes().add(demolition);
        sWallace.getProjectTypes().add(renovation);

        records.add(sWallace);

        return records;






    }
}
