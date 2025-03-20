/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprint.controllers;

import java.util.Set;

import edu.eci.arsw.blueprint.model.Blueprint;
import edu.eci.arsw.blueprint.services.BlueprintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/api")
public class BlueprintAPIController {
    private final BlueprintService bps;
    private final Logger logger = LoggerFactory.getLogger(BlueprintAPIController.class);
    public BlueprintAPIController(BlueprintService bps){
        this.bps = bps;
    }

    /**
     * Get all blueprints
     * @return all blueprints
     */
    @GetMapping("/blueprints")
    public Set<Blueprint> allBlueprints() {
        return bps.getAllBlueprints();
    }

    /**
     * Get blueprints by author
     * @param author author name
     * @return blueprints by author
     */
    @GetMapping("/blueprints/{author}")
    public ResponseEntity<Set<Blueprint>> blueprintsByAuthor(@PathVariable String author) {
        Set<Blueprint> blueprints = bps.getBlueprintsByAuthor(author);
        HttpStatus status = (blueprints == null || blueprints.isEmpty()) ? HttpStatus.NOT_FOUND: HttpStatus.OK;
        return ResponseEntity
                .status(status)
                .body(blueprints);
    }

    /**
     * Add a new blueprint
     * @param bp blueprint to add
     * @return response entity
     */
    @PostMapping("/blueprints")
    public ResponseEntity<String> addNewBlueprint(@RequestBody Blueprint bp) {
        HttpStatus status;
        String message;
        try {
            bps.addNewBlueprint(bp);
            status = HttpStatus.CREATED;
            message="Blueprint created successfully";
        } catch (Exception e) {
            logger.error("Error while adding a new blueprint", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message="Error while creating the blueprint";
        }
        return ResponseEntity
                .status(status)
                .body(message);
    }

    /**
     * Get blueprint by author and name
     * @param author author name
     * @param bpname blueprint name
     * @return blueprint
     */
    @GetMapping("/blueprints/{author}/{bpname}")
    public ResponseEntity<Blueprint> getBlueprint(@PathVariable String author, @PathVariable String bpname) {
        Blueprint blueprint = null;
        HttpStatus status;
        try {
            blueprint = bps.getBlueprint(author, bpname);
            status = (blueprint == null) ? HttpStatus.NOT_FOUND: HttpStatus.OK;
        } catch (Exception e) {
            logger.error("Error while getting a blueprint", e);
            status=HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity
                .status(status)
                .body(blueprint);
    }

    @PutMapping("bluprints/{author}/{bpname}")
    public ResponseEntity<String> updateBlueprint(@RequestBody Blueprint bp, @PathVariable String author, @PathVariable String bpname) {
        HttpStatus status;
        String message;
        try {
            bps.updateBlueprint(author, bpname, bp);
            status = HttpStatus.OK;
            message = "Blueprint updated successfully";
        } catch (Exception e) {
            logger.error("Error while updating a blueprint", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error while updating the blueprint";
        }
        return ResponseEntity
                .status(status)
                .body(message);
    }

    @DeleteMapping("blueprints/{author}/{bpname}")
    public ResponseEntity<String> deleteBlueprint(@PathVariable String author, @PathVariable String bpname) {
        HttpStatus status;
        String message;
        try {
            bps.deleteBlueprint(author, bpname);
            status = HttpStatus.OK;
            message = "Blueprint deleted successfully";
        } catch (Exception e) {
            logger.error("Error while deleting a blueprint", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error while deleting the blueprint";
        }
        return ResponseEntity
                .status(status)
                .body(message);
    }

}

