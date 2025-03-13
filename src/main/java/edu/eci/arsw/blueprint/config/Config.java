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
        Point[] pts = new Point[] { new Point(140, 140), new Point(115, 115) };
        try {
            response.saveBlueprint(new Blueprint("juan", "paint 1", pts));
            response.saveBlueprint(new Blueprint("juan", "paint 2", pts));
            response.saveBlueprint(new Blueprint("_authorname_3_", "_bpname_3", pts));

            // otros datos
            Point[] pts1 = new Point[] {
                    new Point(250, 200), // A
                    new Point(150, 200), // B
                    new Point(200, 250), // C
                    new Point(250, 200), // D
                    new Point(150, 100), // E
                    new Point(250, 100), // F
                    new Point(150, 200) // G
            };
            response.saveBlueprint(new Blueprint("leonardo", "corbata", pts1));

            Point[] pts2 = new Point[] {
                    new Point(100, 200), // A (frontal del carro)
                    new Point(300, 200), // B (frontal derecho)
                    new Point(300, 150), // C (techo derecho)
                    new Point(250, 130), // D (parabrisas derecho)
                    new Point(150, 130), // E (parabrisas izquierdo)
                    new Point(100, 150), // F (techo izquierdo)
                    new Point(100, 200), // G (cierra la figura)

                    // Rueda izquierda (un cuadrado)
                    new Point(120, 220), // H
                    new Point(150, 220), // I
                    new Point(150, 250), // J
                    new Point(120, 250), // K
                    new Point(120, 220), // H (cierra la rueda)

                    // Rueda derecha (un cuadrado)
                    new Point(250, 220), // L
                    new Point(280, 220), // M
                    new Point(280, 250), // N
                    new Point(250, 250), // O
                    new Point(250, 220) // L (cierra la rueda)
            };
            response.saveBlueprint(new Blueprint("manuel", "carro", pts2));

            Point[] pts3 = new Point[] {
                    new Point(100, 200), // A (base izquierda)
                    new Point(300, 200), // B (base derecha)
                    new Point(300, 100), // C (techo derecho)
                    new Point(200, 50), // D (pico del techo)
                    new Point(100, 100), // E (techo izquierdo)
                    new Point(100, 200), // A (cierra la base)

                    // Puerta (un rectángulo en el centro)
                    new Point(175, 200), // F (puerta izquierda)
                    new Point(225, 200), // G (puerta derecha)
                    new Point(225, 150), // H (puerta arriba derecha)
                    new Point(175, 150), // I (puerta arriba izquierda)
                    new Point(175, 200) // F (cierra la puerta)
            };
            response.saveBlueprint(new Blueprint("sofia", "casa", pts3));

            Point[] pts4 = new Point[] {
                    new Point(150, 200), // A (base izquierda de la cara)
                    new Point(250, 200), // B (base derecha de la cara)
                    new Point(250, 100), // C (parte superior derecha de la cara)
                    new Point(200, 50), // D (oreja derecha)
                    new Point(150, 100), // E (parte superior izquierda de la cara)
                    new Point(100, 50), // F (oreja izquierda)
                    new Point(150, 100), // E (conexión oreja izquierda con la cara)
                    new Point(150, 200), // A (cierra la cara)

                    // Ojos
                    new Point(170, 150), // G (ojo izquierdo)
                    new Point(180, 150), // H (ojo izquierdo derecho)
                    new Point(220, 150), // I (ojo derecho)
                    new Point(230, 150), // J (ojo derecho derecho)

                    // Bigotes (líneas horizontales)
                    new Point(130, 170), // K (bigote izquierdo extremo)
                    new Point(170, 170), // G (conexión con la cara)
                    new Point(130, 180), // L (bigote izquierdo medio)
                    new Point(170, 180), // G (conexión con la cara)

                    new Point(230, 170), // M (bigote derecho extremo)
                    new Point(190, 170), // I (conexión con la cara)
                    new Point(230, 180), // N (bigote derecho medio)
                    new Point(190, 180) // I (conexión con la cara)
            };
            response.saveBlueprint(new Blueprint("maria", "gato", pts4));

            Point[] pts5 = new Point[] {
                    // Marco del televisor
                    new Point(100, 100), // A (esquina superior izquierda)
                    new Point(300, 100), // B (esquina superior derecha)
                    new Point(300, 250), // C (esquina inferior derecha)
                    new Point(100, 250), // D (esquina inferior izquierda)
                    new Point(100, 100), // A (cierra el marco)

                    // Pantalla
                    new Point(130, 130), // E (esquina superior izquierda de la pantalla)
                    new Point(270, 130), // F (esquina superior derecha de la pantalla)
                    new Point(270, 220), // G (esquina inferior derecha de la pantalla)
                    new Point(130, 220), // H (esquina inferior izquierda de la pantalla)
                    new Point(130, 130), // E (cierra la pantalla)

                    // Botones
                    new Point(280, 160), // I (botón 1)
                    new Point(280, 180), // J (botón 2)
                    new Point(280, 200), // K (botón 3)

                    // Antena
                    new Point(150, 80), // L (antena izquierda)
                    new Point(100, 50), // M (punto superior de la antena izquierda)

                    new Point(250, 80), // N (antena derecha)
                    new Point(300, 50) // O (punto superior de la antena derecha)
            };
            response.saveBlueprint(new Blueprint("luis", "tv", pts5));
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
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}
