package com.myproject.spring5recordapp.domain;

import lombok.*;

import java.math.BigDecimal;
import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"record"})
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @ManyToOne
    private Record record;

    public Material() {
    }

    public Material(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Material(String description, BigDecimal amount, UnitOfMeasure uom, Record record) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.record = record;
    }


}
