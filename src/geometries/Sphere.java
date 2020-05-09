package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * The type Sphere.
 */
public class Sphere extends RadialGeometry {

    private final Point3D _center;

    /**
     * Instantiates a new Sphere.
     *
     * @param center the center
     * @param r      the radius
     */
    public Sphere(Point3D center, double r) {
        super(r);
        _center = new Point3D(center);
    }

    public Sphere(double radius, Point3D center, Color emmission) {
        super(radius, emmission);
        _center = new Point3D(center);
    }

    @Override
    public String toString() {
        return "Sphere : center : " + _center + "Radius(:" + this.getRadius() + ')';
    }

    @Override
    public Vector getNormal(Point3D p) {
        return p.subtract(_center).normalize();
    }

    @Override
    public List<GeoPoint> findIntsersections(Ray ray) {
        Point3D point = ray.getP();
        Vector vec = ray.getDirection();
        Vector tmp;
        try {
            tmp = _center.subtract(point);
        } catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this, (ray.getTargetPoint(getRadius()))));
        }
        double tm = alignZero(vec.dotProduct(tmp));
        double dSquared = (tm == 0) ? tmp.lengthSquared() : tmp.lengthSquared() - tm * tm;
        double thSquared = alignZero(getRadius() * getRadius() - dSquared);
        if (thSquared <= 0) return null;
        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0)
            return List.of(new GeoPoint(this, (ray.getTargetPoint(t1))), new GeoPoint(this, (ray.getTargetPoint(t2))));
        if (t1 > 0)
            return List.of(new GeoPoint(this, (ray.getTargetPoint(t1))));
        else
            return List.of(new GeoPoint(this, (ray.getTargetPoint(t2))));
    }
}
