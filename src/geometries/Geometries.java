package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.*;

public class Geometries implements Intersectable {
    private LinkedList<Intersectable> _shapes;

    public Geometries() {
        _shapes = new LinkedList<Intersectable>();
    }

    public List<Intersectable> getShapes() {
        return _shapes;
    }

    public Geometries(Intersectable... geometries) {
        _shapes = new LinkedList<Intersectable>();
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(_shapes, geometries);
    }

    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        List<Point3D> intersections = null;
        for (Intersectable shape : _shapes) {
            List<Point3D> tempIntersections = shape.findIntsersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}