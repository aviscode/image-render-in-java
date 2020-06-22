package geometries;

import primitives.*;
import primitives.Vector;

import java.util.*;


/**
 * The interface Intersectable.
 */
public abstract class Intersectable {
    private static boolean _boudingVolume = false;
    protected double _minX, _minY, _minZ, _maxX, _maxY, _maxZ;

    /**
     *
     */
    public static void enableBoundingVolume() {
        _boudingVolume = true;
    }

    public abstract void setBox();
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
     * Gets min x.
     *
     * @return the min x
     */
    public double getMinX() {
        return _minX;
    }

    /**
     * Gets min y.
     *
     * @return the min y
     */
    public double getMinY() {
        return _minY;
    }

    /**
     * Gets min z.
     *
     * @return the min z
     */
    public double getMinZ() {
        return _minZ;
    }

    /**
     * Gets max x.
     *
     * @return the max x
     */
    public double getMaxX() {
        return _maxX;
    }

    /**
     * Gets max y.
     *
     * @return the max y
     */
    public double getMaxY() {
        return _maxY;
    }

    /**
     * Gets max z.
     *
     * @return the max z
     */
    public double getMaxZ() {
        return _maxZ;
    }

    /**
     * check if ray intersect with the box
     *
     * @param ray the ray
     * @return true if intersect
     */
    public boolean isIntersect(Ray ray) {
        Point3D head = ray.getDirection().getHead();
        Point3D p = ray.getP();
        double temp;

        double dirX = head.getX(), dirY = head.getY(), dirZ = head.getZ();
        double origX = p.getX(), origY = p.getY(), origZ = p.getZ();

        // Min/Max starts with X
        double tMin = (_minX - origX) / dirX, tMax = (_maxX - origX) / dirX;
        if (tMin > tMax) { temp = tMin; tMin = tMax; tMax = temp; }        //swap

        double tYMin = (_minY - origY) / dirY, tYMax = (_maxY - origY) / dirY;
        if (tYMin > tYMax) { temp = tYMin; tYMin = tYMax; tYMax = temp; }  //swap
        if ((tMin > tYMax) || (tYMin > tMax)) return false;
        if (tYMin > tMin) tMin = tYMin;
        if (tYMax < tMax) tMax = tYMax;

        double tZMin = (_minZ - origZ) / dirZ, tZMax = (_maxZ - origZ) / dirZ;
        if (tZMin > tZMax){ temp = tZMin; tZMin = tZMax; tZMax = temp; }          //swap
        return tMin <= tZMax && tZMin <= tMax;
    }

    /**
     * List of Intersection points found in tests
     * Find intersections list between giving ray and geometries shapes.
     * the returned list can be null if there is no intersections or there is no geometries shape in Geometries.
     *
     * @param ray the ray
     * @return the list of Points 3D Intersections
     */
    public abstract List<GeoPoint> findIntsersections(Ray ray);

    /**
     * List of Intersection points found in tests
     * Find intersections list between giving ray and geometries shapes in a bound box.
     * the returned list can be null or empty if there is no intersections or there is no geometries shape in Geometries.
     *
     * @param ray the ray
     * @return the list of Points 3D Intersections or a empty list in case the ray is not in the box.
     */
    public List<GeoPoint> findIntsersectionsBound(Ray ray) {
        return _boudingVolume && !isIntersect(ray) ? null : findIntsersections(ray);
    }

    /**
     * get the box size
     *
     * @return box size
     */
    public double getBoxSize() {
        return (_maxX - _minX) * (_maxY - _minY) * (_maxZ - _minZ);
    }
}