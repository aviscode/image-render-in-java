package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

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
        return null;
    }

    @Override
    public String toString() {
        return "Tube :" + "axis Ray :(" + _axisRay + ") Radius :(" + this.getRadius()+')';
    }
}
