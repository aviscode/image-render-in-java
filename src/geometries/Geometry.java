package geometries;

import primitives.*;

/**
 * The interface Geometry.
 */
public interface Geometry {
    /**
     * Get normal vector.
     *
     * @param p the point
     * @return the normal to a vector
     */
    public Vector getNormal(Point3D p);
}
