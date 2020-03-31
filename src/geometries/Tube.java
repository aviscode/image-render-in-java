package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * The type Tube.
 */
public class Tube extends RadialGeometry {

    private Ray _axisRay;

    /**
     * Instantiates a new Tube.
     *
     * @param _ray the ray
     * @param r    the r
     */
    public Tube(Ray _ray, double r) {
        super(r);
        _axisRay = _ray;
    }

    /**
     * Gets the axis of the tube .
     *
     * @return the ray
     */
    public Ray gatAxisRay() {
        return _axisRay;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Point3D p0 = _axisRay.getP();
        Vector v;
        try {
            v = p.subtract(p0);
        } catch (Exception e) {
            return _axisRay.getDirection();
        }
        double temp = alignZero(_axisRay.getDirection().dotProduct(v));
        if (temp == 0)
            return p.subtract(p0).normalize();
        Point3D p1 = p0.add(_axisRay.getDirection().scale(temp));
        return p.subtract(p1).normalize();
    }

    @Override
    public String toString() {
        return "Tube :" + "axis Ray :(" + _axisRay + ") Radius :(" + this.getRadius() + ')';
    }
}
