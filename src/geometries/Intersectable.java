package geometries;

import primitives.*;

import java.util.*;

/**
 * The interface Intersectable.
 * TODO decimation and explan more.
 *
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
