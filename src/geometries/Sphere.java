package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * The type Sphere.
 */
public class Sphere extends RadialGeometry {

    private Point3D _center;

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

    @Override
    public String toString() {
        return "Sphere : center : " + _center +"Radius(:"+ this.getRadius()+')';
    }

    @Override
    public Vector getNormal(Point3D p) {
        return p.subtract(_center).normalize();
    }
}
