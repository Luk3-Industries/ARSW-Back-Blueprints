/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprint.persistence;

import edu.eci.arsw.blueprint.model.Blueprint;

import java.util.Set;

/**
 *
 * @author hcadavid
 */
public interface BlueprintsPersistence {
    
    /**
     * 
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;
    
    /**
     * 
     * @param author blueprint's author
     * @param bprintname blueprint's author
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    Blueprint getBlueprint(String author,String bprintname) throws BlueprintNotFoundException;


    /**
     * This method should return all the available blueprints.
     * @return all the blueprints
     */
    Set<Blueprint> getAllBlueprints();

    /**
     * This method should return all the blueprints of an author.
     * @param author blueprint's author
     * @return all the blueprints of the given author
     */
    Set<Blueprint> getBlueprintsByAuthor(String author);

    /**
        * This method should update a blueprint.
        * @param author blueprint's author
        * @param bprintname blueprint's name
        * @param bp the new blueprint
        * @throws BlueprintNotFoundException if there is no such blueprint
        * @throws BlueprintPersistenceException if any other low-level persistence error occurs.
    */
    void updateBlueprint(String author,String bprintname,Blueprint bp) throws BlueprintNotFoundException, BlueprintPersistenceException;
}
