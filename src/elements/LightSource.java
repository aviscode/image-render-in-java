package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The interface Light source.
 */
public interface LightSource {
    /**
     * Get light source intensity as it reaches a point
     *
     * @param p the lighted point
     * @return intensity
     */
    public Color getIntensity(Point3D p);

    /**
     * Get normalized vector in the direction from light source
     * towards the lighted point*
     *
     * @param p the p
     * @return the l
     */
    public Vector getL(Point3D p);

    //TODO
    double getDistance(Point3D point);

}
