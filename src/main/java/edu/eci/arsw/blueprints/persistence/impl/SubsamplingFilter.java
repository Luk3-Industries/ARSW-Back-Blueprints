package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubsamplingFilter implements BlueprintFilter {
    @Override
    public void filter(Blueprint bp) {
        List<Point> points = bp.getPoints();
        List<Point> filteredPoints = new ArrayList<>();
        for (int i = 0; i < points.size(); i+=2) {
            filteredPoints.add(points.get(i));
        }
        bp.setPoints(filteredPoints);
    }
}
