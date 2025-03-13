package edu.eci.arsw.blueprint.services;

import edu.eci.arsw.blueprint.model.Blueprint;
import edu.eci.arsw.blueprint.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprint.persistence.BlueprintPersistenceException;

import java.util.Set;

public interface BlueprintService {
    void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException;
    Set<Blueprint> getAllBlueprints();
    Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException;
    Set<Blueprint> getBlueprintsByAuthor(String author);
    void updateBlueprint(String author,String  bpname,Blueprint bp) throws BlueprintNotFoundException, BlueprintPersistenceException;
    void deleteBlueprint(String author,String name) throws BlueprintNotFoundException;
}
