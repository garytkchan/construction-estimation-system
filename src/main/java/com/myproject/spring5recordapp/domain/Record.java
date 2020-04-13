package com.myproject.spring5recordapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobName;
    private String dateToday;
    private String estimator;
    private String duration;
    private String dateStart;
    private String workScope;
    private String labor;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "record")
    private Set<Material> materials = new HashSet<>();

    @Lob
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private BuildingType buildingType;

    @OneToOne(cascade =  CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "record_projectType",
    joinColumns = @JoinColumn(name = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "projectType_id"))
    private Set<ProjectType> projectTypes = new HashSet<>();



    public void setNotes(Notes notes) {

            this.notes = notes;
            notes.setRecord(this);

    }

    public Record addMaterial(Material material) {
        material.setRecord(this);
        this.materials.add(material);
        return this;
    }


}
