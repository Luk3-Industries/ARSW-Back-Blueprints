package edu.eci.arsw.blueprints.main;


import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    private static final Random random = new Random();
    private static final List<String> names = Arrays.asList("Cesar", "Juan", "Camilo", "Andres", "Felipe", "Carlos", "Daniel", "David", "Esteban", "Fernando");
    private static final int SLEEP_TIME = 1700;

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml"); // Load the Spring configuration file
        BlueprintsServices blueprintsServices = ac.getBean(BlueprintsServices.class); // Get the bean from the Spring application context

        // create some blueprints
        for(String name: names){
            createSomeBlueprints(blueprintsServices, name, 50);
            System.out.println("Created blueprints for: " + name);
        }

        // Print blueprints by author
        printByAuthor(blueprintsServices);

        // Print a random blueprint
        printRandomBlueprint(blueprintsServices);
    }

    private static void printRandomBlueprint(BlueprintsServices blueprintsServices) throws Exception{
        String randomName2 = names.get(random.nextInt(names.size()));
        String paintName = "paint " + random.nextInt(50);
        Blueprint blueprint = blueprintsServices.getBlueprint(randomName2, paintName);
        Thread.sleep(SLEEP_TIME);

        System.out.println("\nBlueprint found for: " + randomName2 + " with paint: " + paintName);
        System.out.println("Result: " + blueprint.getName() + " - " + blueprint.getAuthor() + " - " + blueprint.getPoints().stream().map(Point::toString).reduce((a,b)-> a + ", " + b).get());
    }

    private static void createSomeBlueprints(BlueprintsServices blueprintsServices, String name, int times) throws BlueprintPersistenceException {
        for (int i = 0; i < times; i++) {
            Point[] pts = new Point[]{
                    new Point(random.nextInt(100), random.nextInt( 100)),
                    new Point(random.nextInt(200), random.nextInt( 200))};
            blueprintsServices.addNewBlueprint(new Blueprint(name,"paint " + i, pts));
        }
    }

    private static void printByAuthor(BlueprintsServices blueprintsServices) throws InterruptedException {
        Thread.sleep(SLEEP_TIME);
        System.out.println("Blueprints created.... ");
        Thread.sleep(SLEEP_TIME);
        System.out.println("\nTotal blueprints: " + blueprintsServices.getAllBlueprints().size());
        String randomName = names.get(random.nextInt(names.size()));
        System.out.println("\nBlueprints by Author: " + randomName);
        Thread.sleep(SLEEP_TIME);
        blueprintsServices.getBlueprintsByAuthor(randomName).forEach(
                blueprint -> System.out.println("Author: " + blueprint.getAuthor() + "\t - \tBlueprint: " + blueprint.getName() +
                        "\t - \tPoints: " + blueprint.getPoints().stream().map(Point::toString).reduce((a,b)-> a + ", " + b).get()
                )
        );
    }

}
