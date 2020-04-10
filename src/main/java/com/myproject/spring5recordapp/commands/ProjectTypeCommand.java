package com.myproject.spring5recordapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProjectTypeCommand {

    private Long id;
    private String description;
}
