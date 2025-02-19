package edu.eci.arsw.blueprints.services.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that implements the subsampling filter for blueprints.
 * This filter removes alternate points in a blueprint, thus
 * reducing the total number of points.
 */
@Service
public class SubsamplingFilter implements BlueprintFilter {

    /**
     * Filters points from a blueprint by removing alternate points.
     *
     * @param bp the blueprint to filter
     */
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