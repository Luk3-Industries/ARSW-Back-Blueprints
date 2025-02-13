package edu.eci.arsw.blueprints.main;


import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices blueprintsServices = ac.getBean(BlueprintsServices.class);

        // Arrange
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        blueprintsServices.addNewBlueprint(new Blueprint("Santiago", "Monaliza", pts));

        // Act
        Blueprint bp = blueprintsServices.getBlueprint("Santiago", "Monaliza");
        System.out.println(bp.getAuthor()+ " + " + bp.getName());

        blueprintsServices.getBlueprintsByAuthor("Santiago");
    }
}
