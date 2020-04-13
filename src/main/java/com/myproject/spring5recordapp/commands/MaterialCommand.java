package com.myproject.spring5recordapp.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MaterialCommand {

    private Long id;
    private Long recordId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;


}
