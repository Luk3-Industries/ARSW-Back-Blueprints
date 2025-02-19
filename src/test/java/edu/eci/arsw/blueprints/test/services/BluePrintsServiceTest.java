package edu.eci.arsw.blueprints.test.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintFilter;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.persistence.impl.RedundancyFilter;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class BluePrintsServiceTest {
    @Test
    public void getBlueprintTest() {
        // Arrange
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        BlueprintFilter blueprintFilter = new RedundancyFilter();
        BlueprintsServices blueprintsServices = new BlueprintsServices(ibpp, blueprintFilter);

        Point[] pts = new Point[]{new Point(61, 2), new Point(80, 37)};
        Blueprint bp = new Blueprint("john", "thepaint", pts);
        // Act
        try {
            blueprintsServices.addNewBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Should not fail with error: " + ex.getMessage());
        }
        // Assert
        try {
            assertEquals("The blueprint returned is not the expected one", ibpp.getBlueprint("john", "thepaint"), bp);
            assertEquals("The blueprint returned is not the expected one", blueprintsServices.getBlueprint("john", "thepaint"), bp);
        } catch (BlueprintNotFoundException ex) {
            fail("The blueprint was not found");
        }
    }

    @Test
    public void shouldNotGetBluePrint(){
        // Arrange
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        BlueprintFilter blueprintFilter = new RedundancyFilter();
        BlueprintsServices blueprintsServices = new BlueprintsServices(ibpp, blueprintFilter);
        // Act
        Blueprint bpFound = null;
        try {
            bpFound = blueprintsServices.getBlueprint("john", "thepaint");
            fail("Should not find the blueprint");
        } catch (BlueprintNotFoundException ex) {
            assertEquals("The given blueprint does not exist: john thepaint", ex.getMessage());
        }
        // Assert
        assertNull("The blueprint returned is not the expected one", bpFound);
    }

    @Test
    public void shouldGetAllBlueprintsByAuthor() {
        // Arrange
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        BlueprintFilter blueprintFilter = new RedundancyFilter();
        BlueprintsServices blueprintsServices = new BlueprintsServices(ibpp, blueprintFilter);
        Set<Blueprint> blueprints = null;
        int times = 100;
        String name = "Same name";
        for(int i=0; i < times; i ++){
            Point[] pts = new Point[]{new Point(1 + i, 2+i), new Point(80+i, 37+i)};
            Blueprint bp = new Blueprint(name, "Paint test: " + i, pts);
            // Act
            try {
                blueprintsServices.addNewBlueprint(bp);
            } catch (BlueprintPersistenceException ex) {
                fail("Should not fail with error: " + ex.getMessage());
            }
        }

        // Assert
        blueprints = blueprintsServices.getBlueprintsByAuthor(name);

        assertEquals("The blueprints returned are not the expected ones", times, blueprints.size());
        for(Blueprint bp: blueprints) {
            assertEquals("The blueprint returned is not the expected one", name, bp.getAuthor());
        }
    }

    @Test
    public void shouldNotGetBluePrintsByAuthor(){
        // Arrange
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        BlueprintFilter blueprintFilter = new RedundancyFilter();
        BlueprintsServices blueprintsServices = new BlueprintsServices(ibpp, blueprintFilter);
        Set<Blueprint> blueprints = null;

        // Act
        blueprints = blueprintsServices.getBlueprintsByAuthor("john");

        // Assert
        assertEquals("The blueprints returned are not the expected ones", 0, blueprints.size());
    }

    @Test
    public void shouldGetAllBlueprints() {
        // Arrange
        BlueprintFilter blueprintFilter = new RedundancyFilter();
        BlueprintsServices blueprintsServices = new BlueprintsServices(new InMemoryBlueprintPersistence(), blueprintFilter);
        Set<Blueprint> blueprints = null;
        int times = 100;
        for (int i = 0; i < times; i++) {
            Point[] pts = new Point[]{new Point(1 + i, 2 + i), new Point(80 + i, 37 + i)};
            Blueprint bp = new Blueprint("Same name", "Paint test: " + i, pts);
            try {
                blueprintsServices.addNewBlueprint(bp);
            } catch (BlueprintPersistenceException ex) {
                fail("Should not fail with error: " + ex.getMessage());
            }
        }
        // Act
        blueprints = blueprintsServices.getAllBlueprints();

        // Assert
        assertEquals("The blueprints returned are not the expected ones", times, blueprints.size());
    }

    @Test
    public void shouldNotReturnBluePrints(){
        // Arrange
        BlueprintFilter blueprintFilter = new RedundancyFilter();
        BlueprintsServices blueprintsServices = new BlueprintsServices(new InMemoryBlueprintPersistence(), blueprintFilter);
        Set<Blueprint> blueprints = null;
        // Act
        blueprints = blueprintsServices.getAllBlueprints();
        // Assert
        assertEquals("The blueprints returned are not the expected ones", 0, blueprints.size());
    }
}
