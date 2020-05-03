package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

/**
 * The type Cylinder.
 */
public class Cylinder extends Tube {
    /**
     * The Height.
     */
    double _height;

    /**
     * Instantiates a new Cylinder.
     *
     * @param height the height
     * @param r      the radius
     * @param _ray   the ray
     */
    public Cylinder(double height, double r, Ray _ray) {
        super(_ray, r);
        _height = height;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder : " + "height :(" + _height + ") Radius :(" + this.getRadius() + ')';
    }

    @Override
    public Vector getNormal(Point3D p) {
        Vector v = this.gatAxisRay().getDirection();
        Point3D p0 = this.gatAxisRay().getP();
        if (p.equals(p0))
            return v;
        else {
            double temp = v.dotProduct(p.subtract(p0));
            if (isZero(temp) || isZero(temp - _height))
                return v;
            return p.subtract(p0.add(v.scale(temp))).normalize();
        }
    }
}
