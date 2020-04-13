package com.myproject.spring5recordapp.services;

import com.myproject.spring5recordapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
