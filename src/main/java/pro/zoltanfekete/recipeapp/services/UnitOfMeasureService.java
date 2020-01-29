package pro.zoltanfekete.recipeapp.services;

import pro.zoltanfekete.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
