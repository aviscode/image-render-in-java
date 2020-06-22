package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * The type Plane.
 */
public class Plane extends Geometry {
    private Point3D _point;
    private Vector _normal;

    /**
     * Instantiates a new Plane.
     *
     * @param emissionLight the emission light
     * @param material      the material
     * @param p1            the p 1
     * @param p2            the p 2
     * @param p3            the p 3
     */
    public Plane(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight, material);
        _point = new Point3D(p1);
        _normal = p2.subtract(p1).crossProduct(p3.subtract(p1)).normalize();
    }

    /**
     * Instantiates a new Plane.
     *
     * @param emissionLight the emission light
     * @param p1            the p 1
     * @param p2            the p 2
     * @param p3            the p 3
     */
    public Plane(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        this(emissionLight, new Material(0, 0, 0), p1, p2, p3);
    }

    /**
     * Instantiates a new Plane.
     *
     * @param p1 the p 1
     * @param p2 the p 2
     * @param p3 the p 3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        this(Color.BLACK, p1, p2, p3);
    }

    /**
     * Instantiates a new Plane.
     *
     * @param normal        the normal
     * @param point         the point
     * @param emissionLight the emission light
     * @param material      the material
     */
    public Plane(Color emissionLight, Material material,Vector normal, Point3D point) {
        super(emissionLight, material);
        _point = new Point3D(point);
        _normal = new Vector(normal).normalize();
    }

    /**
     * Instantiates a new Plane.
     *
     * @param normal    the normal
     * @param point     the point
     * @param emmission the emmission
     */
    public Plane(Color emmission, Vector normal, Point3D point) {
        this(emmission, new Material(0, 0, 0), normal, point);
    }

    /**
     * Instantiates a new Plane.
     *
     * @param point  the point
     * @param normal the normal
     */
    public Plane(Point3D point, Vector normal) {
        this(Color.BLACK, new Material(0, 0, 0), normal, point);
    }

    /**
     * Gets point.
     *
     * @return the point
     */
    public Point3D getPoint() {
        return _point;
    }

    /**
     * Gets normal.
     *
     * @return the normal
     */
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public String toString() {
        return "Plane : " + _point + " Normal : " + _normal;
    }

    /**
     * Gets normal.
     *
     * @param p the p
     * @return the normal
     */
    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    @Override
    public void setBox() {

    }

    @Override
    public List<GeoPoint> findIntsersections(Ray ray) {
        Vector p;
        try {
            p = _point.subtract(ray.getP());
        } catch (IllegalArgumentException e) {
            return null;
        }
        double nv = _normal.dotProduct(ray.getDirection());
        if (isZero(nv)) return null;
        double temp = alignZero(_normal.dotProduct(p) / nv);
        return temp <= 0 ? null : List.of(new GeoPoint(this, (ray.getTargetPoint(temp))));
    }
}
