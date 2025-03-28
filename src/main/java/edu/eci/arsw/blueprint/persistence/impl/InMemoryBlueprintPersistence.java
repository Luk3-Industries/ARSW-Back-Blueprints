/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprint.persistence.impl;

import edu.eci.arsw.blueprint.model.Blueprint;
import edu.eci.arsw.blueprint.model.Point;
import edu.eci.arsw.blueprint.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprint.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprint.persistence.BlueprintsPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final ConcurrentHashMap<Tuple<String,String>,Blueprint> blueprints = new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.putIfAbsent(new Tuple<>(bp.getAuthor(), bp.getName()), bp) != null) {
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        Blueprint blueprint =  blueprints.get(new Tuple<>(author, bprintname));
        if (blueprint == null) {
            throw new BlueprintNotFoundException("The given blueprint does not exist: " + author + " " + bprintname);
        }
        return blueprint;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        Set<Blueprint> allBlueprints = new HashSet<>();
        blueprints
                .forEach((key, value) -> allBlueprints.add(value));
        // Delete stub data
        allBlueprints.removeIf(bp -> bp.getAuthor().equals("_authorname_"));
        return allBlueprints;
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) {
        Set<Blueprint> authorBlueprints = new HashSet<>();
        blueprints
                .entrySet()
                .stream()
                .filter(bp -> bp.getKey().getElem1().equals(author))
                .forEachOrdered(bp -> authorBlueprints.add(bp.getValue()));
        return authorBlueprints;
    }

    @Override
    public void updateBlueprint(String author, String bprintname, Blueprint bp) throws BlueprintNotFoundException, BlueprintPersistenceException {
        if (!blueprints.containsKey(new Tuple<>(author, bprintname))) {
            throw new BlueprintNotFoundException("The given blueprint does not exist: " + author + " " + bprintname);
        }
        blueprints.replace(new Tuple<>(author, bprintname), bp);
    }

    @Override
    public void deleteBlueprint(String author, String name) throws BlueprintNotFoundException {
        if (blueprints.remove(new Tuple<>(author, name)) == null) {
            throw new BlueprintNotFoundException("The given blueprint does not exist: " + author + " " + name);
        }
        blueprints.remove(new Tuple<>(author, name));
    }

}
