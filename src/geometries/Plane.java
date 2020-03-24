package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * The type Plane.
 */
public class Plane implements Geometry {
    private Point3D _point;
    private Vector _normal;

    /**
     * Instantiates a new Plane.
     *
     * @param p the other point
     * @param v the Normal vector
     */
    public Plane(Point3D p, Vector v) {
        _point = new Point3D(p);
        _normal = new Vector(v).normalize();
    }

    /**
     * Instantiates a new Plane.
     *
     * @param p1 the p 1
     * @param p2 the p 2
     * @param p3 the p 3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _point = new Point3D(p1);
        _normal = p3.subtract(p2).crossProduct(p3.subtract(p1)).normalize();
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

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
