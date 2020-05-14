package geometries;

import primitives.*;

import java.util.*;

/**
 * The interface Intersectable.
 */
public interface Intersectable {

    /**
     * The type Geo point.
     */
    public static class GeoPoint {
        public Geometry _geometry;
        public Point3D _point;

        /**
         * Instantiates a new Geo point.
         *
         * @param geometry the geometry
         * @param point    the point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            _point = point;
            _geometry = geometry;
        }

        /**
         * Gets geometry.
         *
         * @return the geometry
         */
        public Geometry getGeometry() {
            return _geometry;
        }

        /**
         * Gets point.
         *
         * @return the point
         */
        public Point3D getPoint() {
            return _point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(_geometry, geoPoint._geometry) &&
                    Objects.equals(_point, geoPoint._point);
        }
        @Override
        public int hashCode() {
            return Objects.hash(_geometry, _point);
        }
    }

    /**
     * List of Intersection points found in tests
     * Find intersections list between giving ray and geometries shapes.
     * the returned list can be null if there is no intersections or there is no geometries shape in Geometries.
     *
     * @param ray the ray
     * @return the list of Points 3D Intersections
     */
    List<GeoPoint> findIntsersections(Ray ray);
}