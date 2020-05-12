package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The interface Light source.
 */
public interface LightSource {
    /**TODO
     * Gets intensity.
     *
     * @param p the p
     * @return the intensity
     */
    public Color getIntensity(Point3D p);

    /**
     * Gets l.
     *TODO
     * @param p the p
     * @return the l
     */
    public Vector getL(Point3D p);

}
