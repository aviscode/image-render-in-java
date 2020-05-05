package geometries;

import primitives.*;

import java.util.*;

/**
 * The interface Intersectable.
 * TODO decimation and explan more.
 */
public interface Intersectable {
    /**
     * Find intsersections list.
     *
     * @param ray the ray
     * @return the list of Points 3D Intersections
     */
    List<Point3D> findIntsersections(Ray ray);


    /**
     * The type Geo point.
     */
    public static class GeoPoint {
        /**
         * The Geometry.
         */
        public Geometry _geometry;
        /**
         * The Point.
         */
        public Point3D _point;

        /**
         * Instantiates a new Geo point.
         *
         * @param geometry the geometry
         * @param point    the point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            _geometry = geometry;
            _point = point;
        }
    }

}
