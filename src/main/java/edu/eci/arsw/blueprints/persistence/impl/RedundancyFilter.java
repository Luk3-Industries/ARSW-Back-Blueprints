package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedundancyFilter implements BlueprintFilter {
    @Override
    public void filter(Blueprint bp) {
        System.out.println("Filtering blueprint points...");
        List<Point> points = bp.getPoints();
        List<Point> filteredPoints = new ArrayList<>();
        filteredPoints.add(points.get(0));
        for (int i = 1; i < points.size(); i++) {
            for (int j = i +1; j < points.size(); j++) {
                if (points.get(i).equals(points.get(j))) {
                    break;
                }
            }
            filteredPoints.add(points.get(i));
        }
        bp.setPoints(filteredPoints);
    }
}
