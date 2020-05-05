package geometries;

import primitives.*;

import java.awt.Color;

/**
 * The interface Geometry.
 */
public abstract class Geometry implements Intersectable {

    /**
     * The Emmission.
     */
    protected java.awt.Color _emmission;

    /**
     * Gets emmission.
     *
     * @return the emmission
     */
    public java.awt.Color getEmmission() {
        return _emmission;
    }

    /**
     * Instantiates a new Geometry.
     *
     * @param emmission the emmission
     */
    public Geometry(Color emmission) {
        _emmission = emmission;
    }

    /**
     * Instantiates a new Geometry.
     */
    public Geometry() {
        _emmission = java.awt.Color.black;
    }

    /**
     * Get normal vector.
     *
     * @param p the point
     * @return the normal to a vector
     */
    public abstract Vector getNormal(Point3D p);
}
