/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprint.model.Blueprint;
import edu.eci.arsw.blueprint.model.Point;
import edu.eci.arsw.blueprint.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprint.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprint.persistence.impl.InMemoryBlueprintPersistence;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
         assertEquals("The given blueprint already exists: "+bp2, ex.getMessage());
        }
    }

    @Test
    public void getBlueprintTest() {
        // Arrange
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();

        Point[] pts = new Point[]{new Point(61, 2), new Point(80, 37)};
        Blueprint bp = new Blueprint("john", "thepaint", pts);
        // Act
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Should not fail with error: " + ex.getMessage());
        }
        // Assert
        try {
            assertEquals("The blueprint returned is not the expected one", ibpp.getBlueprint("john", "thepaint"), bp);
            assertEquals("The blueprint returned is not the expected one", ibpp.getBlueprint("john", "thepaint"), bp);
        } catch (BlueprintNotFoundException ex) {
            fail("The blueprint was not found");
        }
    }

    @Test
    public void shouldNotGetBluePrint(){
        // Arrange
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        // Act
        Blueprint bpFound = null;
        try {
            bpFound = ibpp.getBlueprint("john", "thepaint");
            fail("Should fail with error: ");
        } catch (BlueprintNotFoundException ex) {
            // Assert
            assertNull("The blueprint returned is not the expected one", bpFound);
            assertTrue("Los contenidos de los strings no son iguales", ex.getMessage().equals("The given blueprint does not exist: " + "john " + "thepaint"));        }
    }

    @Test
    public void shouldGetAllBlueprintsByAuthor() {
        // Arrange
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Set<Blueprint> blueprints = null;
        int times = 100;
        String name = "Same name";
        for(int i=0; i < times; i ++){
            Point[] pts = new Point[]{new Point(1 + i, 2+i), new Point(80+i, 37+i)};
            Blueprint bp = new Blueprint(name, "Paint test: " + i, pts);
            // Act
            try {
                ibpp.saveBlueprint(bp);
            } catch (BlueprintPersistenceException ex) {
                fail("Should not fail with error: " + ex.getMessage());
            }
        }

        // Assert
        blueprints = ibpp.getBlueprintsByAuthor(name);

        assertEquals("The blueprints returned are not the expected ones", times, blueprints.size());
        for(Blueprint bp: blueprints) {
            assertEquals("The blueprint returned is not the expected one", name, bp.getAuthor());
        }
    }

    @Test
    public void shouldNotGetBluePrintsByAuthor(){
        // Arrange
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Set<Blueprint> blueprints = null;

        // Act
        blueprints = ibpp.getBlueprintsByAuthor("john");

        // Assert
        assertEquals("The blueprints returned are not the expected ones", 0, blueprints.size());
    }

    @Test
    public void shouldGetAllBlueprints() {
        // Arrange
        InMemoryBlueprintPersistence ibpp = new InMemoryBlueprintPersistence();
        Set<Blueprint> blueprints = null;
        int times = 100;
        for (int i = 0; i < times; i++) {
            Point[] pts = new Point[]{new Point(1 + i, 2 + i), new Point(80 + i, 37 + i)};
            Blueprint bp = new Blueprint("Same name", "Paint test: " + i, pts);
            try {
                ibpp.saveBlueprint(bp);
            } catch (BlueprintPersistenceException ex) {
                fail("Should not fail with error: " + ex.getMessage());
            }
        }
        // Act
        blueprints = ibpp.getAllBlueprints();

        // Assert
        assertEquals("The blueprints returned are not the expected ones", times, blueprints.size());
    }

    @Test
    public void shouldNotReturnBluePrints(){
        // Arrange
        InMemoryBlueprintPersistence ibpp =new InMemoryBlueprintPersistence();
        Set<Blueprint> blueprints = null;
        // Act
        blueprints = ibpp.getAllBlueprints();
        // Assert
        assertEquals("The blueprints returned are not the expected ones", 0, blueprints.size());
    }


    
}
