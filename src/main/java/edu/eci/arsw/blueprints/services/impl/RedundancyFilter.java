package edu.eci.arsw.blueprints.services.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that implements the redundancy filter for blueprints.
 * This filter removes redundant points in a blueprint, i.e.,
 * those points that are the same and appear more than once.
 */
@Service
public class RedundancyFilter implements BlueprintFilter {

    /**
     * Filters redundant points from a blueprint.
     *
     * @param bp the blueprint to filter
     */
    @Override
    public void filter(Blueprint bp) {
        List<Point> points = bp.getPoints();
        List<Point> filteredPoints = new ArrayList<>();
        for (Point p : points) {
            if (!filteredPoints.contains(p)) {
                filteredPoints.add(p);
            }
        }
        bp.setPoints(filteredPoints);
    }

}