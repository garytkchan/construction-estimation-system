package com.myproject.spring5recordapp.domain;

import javax.persistence.*;
import java.util.Set;

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
    //private BuildingType buildingType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "record")
    private Set<Material> materials;

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
    private Set<ProjectType> projectTypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDateToday() {
        return dateToday;
    }

    public void setDateToday(String dateToday) {
        this.dateToday = dateToday;
    }

    public String getEstimator() {
        return estimator;
    }

    public void setEstimator(String estimator) {
        this.estimator = estimator;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getWorkScope() {
        return workScope;
    }

    public void setWorkScope(String workScope) {
        this.workScope = workScope;
    }

    public String getLabor() {
        return labor;
    }

    public void setLabor(String labor) {
        this.labor = labor;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Set<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<Material> materials) {
        this.materials = materials;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public Set<ProjectType> getProjectTypes() {
        return projectTypes;
    }

    public void setProjectTypes(Set<ProjectType> projectTypes) {
        this.projectTypes = projectTypes;
    }
}
