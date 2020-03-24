package geometries;

import primitives.Point3D;

/**
 * The type Triangle.
 */
public class Triangle extends Polygon {
    /**
     * Instantiates a new Triangle.
     *
     * @param p1 the Point 1
     * @param p2 the Point 2
     * @param p3 the Point 3
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    @Override
    public String toString() {
        return "Triangle : " + _vertices + " " + _plane;
    }
}
