package geometries;

import primitives.*;

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
     * @param radius      the radius
     * @param ray   the ray
     */
    public Cylinder(double height, double radius, Ray ray) {
        super(ray, radius);
        _height = height;
    }

    public Cylinder(double r, Ray ray, double height, Color emmission) {
        this(height, r, ray);
        _emmission = emmission;
    }

    public Cylinder(double r, Ray ray, double height, Color emmission, Material material) {
        this(r, ray, height, emmission);
        _material = material;
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
