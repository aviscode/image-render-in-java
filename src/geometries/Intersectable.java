package geometries;

import primitives.*;

import java.util.*;

/**
 * The interface Intersectable.
 */
public interface Intersectable {
    /**
     * Find intsersections list.
     *
     * @param ray the ray
     * @return the list of Points 3D Intersections
     */
    List<Point3D> findIntsersections(Ray ray);
}
