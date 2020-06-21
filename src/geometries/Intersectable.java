package geometries;

import primitives.*;
import primitives.Vector;

import java.util.*;


/**
 * The abstract class Intersectable.
 */
public abstract class Intersectable {
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
     * The Min x.
     */
    protected double _minX, /**
     * The Min y.
     */
    _minY, /**
     * The Min z.
     */
    _minZ, /**
     * The Max x.
     */
    _maxX, /**
     * The Max y.
     */
    _maxY, /**
     * The Max z.
     */
    _maxZ;

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
        Vector v = ray.getDirection();
        Point3D p = ray.getP();
        double dirX = v.getHead().getX().get(), dirY = v.getHead().getY().get(), dirZ = v.getHead().getZ().get();
        double origX = p.getX().get(), origY = p.getY().get(), origZ = p.getZ().get();
        double tmin = (_minX - origX) / dirX, tmax = (_maxX - origX) / dirX;
        if (tmin > tmax) tmin += (tmax - (tmax = tmin));        //swap
        double tymin = (_minY - origY) / dirY, tymax = (_maxY - origY) / dirY;
        if (tymin > tymax) tymin += (tymax - (tymax = tymin));  //swap
        if ((tmin > tymax) || (tymin > tmax)) return false;
        if (tymin > tmin) tmin = tymin;
        if (tymax < tmax) tmax = tymax;
        double tzmin = (_minZ - origZ) / dirZ, tzmax = (_maxZ - origZ) / dirZ;
        if (tzmin > tzmax) tzmin = (tzmax - (tzmax = tzmin));          //swap
        if ((tmin > tzmax) || (tzmin > tmax)) return false;
        if (tzmin > tmin) tmin = tzmin;
        if (tzmax < tmax) tmax = tzmax;
        return true;
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
        if (isIntersect(ray)) return findIntsersections(ray);
        else return new ArrayList<GeoPoint>();//in case the ray is not in the box
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