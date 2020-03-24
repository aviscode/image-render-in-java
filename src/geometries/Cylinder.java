package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * The type Cylinder.
 */
public class Cylinder extends RadialGeometry {
    /**
     * The Height.
     */
    double _height;

    /**
     * Instantiates a new Cylinder.
     *
     * @param height the height
     * @param r      the radius
     */
    public Cylinder(double height, double r) {
        super(r);
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
        return "Cylinder : " + "height :(" + _height + ") Radius :("+ this.getRadius()+')';
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
