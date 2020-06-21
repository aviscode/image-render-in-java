package geometries;

import primitives.*;


/**
 * The abstract class Geometry.
 */
public abstract class Geometry extends Intersectable {
    protected Color _emmission;
    protected Material _material;

    /**
     * Instantiates a new Geometry.
     *
     * @param emmission the emmission
     * @param material  the material
     */
    public Geometry(Color emmission, Material material) {
        _emmission = emmission;
        _material = material;
    }

    /**
     * Instantiates a new Geometry.
     *
     * @param emmission the emmission
     */
    public Geometry(Color emmission) {
        this(emmission, new Material(0, 0, 0));
    }

    /**
     * Instantiates a new Geometry.
     */
    public Geometry() {
        this(Color.BLACK, new Material(0, 0, 0));
    }

    /**
     * Gets material.
     *
     * @return the material
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * Gets emmission.
     *
     * @return the emmission
     */
    public Color getEmmission() {
        return _emmission;
    }

    /**
     * Gets the normal vector
     *
     * @param p the point
     */
    public abstract Vector getNormal(Point3D p);
}
