package geometries;

import primitives.*;

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
     * @param emission the emission
     * @param material the material
     * @param radius   the radius
     * @param center   the center
     */
    public Sphere(Color emission, Material material, double radius, Point3D center) {
        super(emission, material, radius);
        _center = new Point3D(center);
    }

    /**
     * Instantiates a new Sphere.
     *
     * @param emissionLight the emission light
     * @param radius        the radius
     * @param center        the center
     */
    public Sphere(Color emissionLight, double radius, Point3D center) {
        this(emissionLight, new Material(0, 0, 0), radius, center);
    }

    /**
     * Instantiates a new Sphere.
     *
     * @param center the center
     * @param radius the radius
     */
    public Sphere(Point3D center, double radius) {
        this(Color.BLACK, new Material(0, 0, 0), radius, center);
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

    @Override
    public void setBox() {
        _minX = _center.getX() - _radius;
        _maxX = _center.getX() + _radius;
        _minY = _center.getY() - _radius;
        _maxY = _center.getY() + _radius;
        _minZ = _center.getZ() - _radius;
        _maxZ = _center.getZ() + _radius;
        _middlePoint = _center;
        _finity = true;
    }
}
