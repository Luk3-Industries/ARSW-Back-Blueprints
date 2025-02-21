package edu.eci.arsw.blueprint.config;

import edu.eci.arsw.blueprint.model.Blueprint;
import edu.eci.arsw.blueprint.model.Point;
import edu.eci.arsw.blueprint.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprint.persistence.impl.InMemoryBlueprintPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private final Logger logger = LoggerFactory.getLogger(Config.class);
    @Bean
    public InMemoryBlueprintPersistence inMemoryBlueprintPersistence() {
        InMemoryBlueprintPersistence response = new InMemoryBlueprintPersistence();
        Point[] pts = new Point[]{new Point(140, 140), new Point(115, 115)};
        try{
            response.saveBlueprint(new Blueprint("juan", "paint 1", pts));
            response.saveBlueprint(new Blueprint("juan", "paint 2", pts));
            response.saveBlueprint(new Blueprint("_authorname_3_", "_bpname_ 3  ", pts));
        } catch (BlueprintPersistenceException e) {
            logger.error("Error while loading In Memory Blueprint Persistence blueprint", e);
        }
        return response;
    }

}
