package geometries;

import primitives.*;

import java.util.*;

/**
 * The interface Intersectable.
 *      * Find intersections list between giving ray and geometries shapes.
 *      * the returned list can be null if there is no intersections or there is no geometries shape in Geometries.
 */
public interface Intersectable {
    /**
     * List of Intersection points found in tests
     *
     * @param ray the ray
     * @return the list of Points 3D Intersections
     */
    List<Point3D> findIntsersections(Ray ray);
}
