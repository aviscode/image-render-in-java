package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The interface Light source.
 */
public interface LightSource {
    /**
     * Gets intensity.
     *
     * @param p the p
     * @return the intensity
     */
    public Color getIntensity(Point3D p);

    /**
     * Gets l.
     *Gets the vector from light source to the given point.
     * @param p the p
     * @return the l
     */
    public Vector getL(Point3D p);

    double getDistance(Point3D point);

}
