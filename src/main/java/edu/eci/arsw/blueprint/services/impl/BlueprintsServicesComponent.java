/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprint.services.impl;

import edu.eci.arsw.blueprint.model.Blueprint;
import edu.eci.arsw.blueprint.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprint.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprint.persistence.BlueprintsPersistence;
import java.util.Set;

import edu.eci.arsw.blueprint.services.BlueprintFilter;
import edu.eci.arsw.blueprint.services.BlueprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServicesComponent implements BlueprintService {

    BlueprintsPersistence bpp;
    private BlueprintFilter blueprintFilter;

    @Autowired
    public BlueprintsServicesComponent(BlueprintsPersistence bpp, @Qualifier("redundancyFilter") BlueprintFilter blueprintFilter) {
        this.bpp = bpp;
        this.blueprintFilter = blueprintFilter;
    }

    /**
     * This method should add a new blueprint.
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists
     */
    @Override
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        blueprintFilter.filter(bp);
        bpp.saveBlueprint(bp);
    }

    /**
     * This method should return all the available blueprints.
     * @return all the blueprints
     */
    @Override
    public Set<Blueprint> getAllBlueprints() {
        return bpp.getAllBlueprints();
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    @Override
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        return bpp.getBlueprint(author, name);
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     */
    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) {
        return bpp.getBlueprintsByAuthor(author);
    }
    
}
