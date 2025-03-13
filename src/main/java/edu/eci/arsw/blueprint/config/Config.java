package edu.eci.arsw.blueprint.config;

import edu.eci.arsw.blueprint.model.Blueprint;
import edu.eci.arsw.blueprint.model.Point;
import edu.eci.arsw.blueprint.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprint.persistence.impl.InMemoryBlueprintPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
            response.saveBlueprint(new Blueprint("_authorname_3_", "_bpname_3", pts));

            // otros datos
            Point[] pts1 = new Point[]{
                    new Point(250, 200), // A
                    new Point(200, 200), // B
                    new Point(200, 250), // C
                    new Point(150, 200), // D
                    new Point(150, 100), // E
                    new Point(250, 100), // F
                    new Point(250, 200)  // G (Mismo que A, cierra la figura)
            };
            response.saveBlueprint(new Blueprint("leonardo", "casa", pts1));

            Point[] pts2 = new Point[]{
                    new Point(50, 50), new Point(70, 80),
                    new Point(90, 110), new Point(110, 140),
                    new Point(130, 170), new Point(150, 200),
                    new Point(170, 230), new Point(190, 260)
            };
            response.saveBlueprint(new Blueprint("manuel", "carro", pts2));

            Point[] pts3 = new Point[]{
                    new Point(200, 200), new Point(220, 210),
                    new Point(240, 220), new Point(260, 230),
                    new Point(280, 240), new Point(300, 250),
                    new Point(320, 260), new Point(340, 270),
                    new Point(360, 280)
            };
            response.saveBlueprint(new Blueprint("sofia", "jirafa", pts3));

            Point[] pts4 = new Point[]{
                    new Point(10, 10), new Point(30, 40),
                    new Point(50, 70), new Point(70, 100),
                    new Point(90, 130), new Point(110, 160),
                    new Point(130, 190), new Point(150, 220)
            };
            response.saveBlueprint(new Blueprint("maria", "estrella", pts4));

            Point[] pts5 = new Point[]{
                    new Point(300, 100), new Point(310, 130),
                    new Point(320, 160), new Point(330, 190),
                    new Point(340, 220), new Point(350, 250),
                    new Point(360, 280), new Point(370, 310)
            };
            response.saveBlueprint(new Blueprint("luis", "barco", pts5));
        } catch (BlueprintPersistenceException e) {
            logger.error("Error while loading In Memory Blueprint Persistence blueprint", e);
        }
        return response;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                logger.info("CORS enabled");
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }


}
