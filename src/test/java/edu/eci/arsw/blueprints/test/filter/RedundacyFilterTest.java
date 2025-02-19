package edu.eci.arsw.blueprints.test.filter;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintFilter;
import edu.eci.arsw.blueprints.services.impl.RedundancyFilter;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RedundacyFilterTest {

    @Test
    public void shouldDeleteTheRedundantPoints() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        BlueprintFilter blueprintFilter = new RedundancyFilter();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15), new Point(40, 40), new Point(15, 15), new Point(40, 40)};
        Blueprint bp0=new Blueprint("mack", "mypaint", pts0);

        blueprintFilter.filter(bp0);
        ibpp.saveBlueprint(bp0);
        assertEquals(2, ibpp.getBlueprint(bp0.getAuthor(), bp0.getName()).getPoints().size());
    }

    @Test
    public void shouldNotDeleteBecauseNotExistRedundantPoints() throws BlueprintPersistenceException, BlueprintNotFoundException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        BlueprintFilter blueprintFilter = new RedundancyFilter();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15), new Point(40, 30), new Point(15, 50)};
        Blueprint bp0=new Blueprint("mack", "mypaint", pts0);

        blueprintFilter.filter(bp0);
        ibpp.saveBlueprint(bp0);
        assertEquals(4, ibpp.getBlueprint(bp0.getAuthor(), bp0.getName()).getPoints().size());
    }

}
