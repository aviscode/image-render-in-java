package elements;

import primitives.Color;

/**
 * The type Ambient light.
 */
public class AmbientLight {
    private final Color _intensity;

    /**
     * Instantiates a new Ambient light.
     *
     * @param iA the a
     * @param kA the k a
     */
    public AmbientLight(Color iA, double kA) {
        _intensity = iA.scale(kA);
    }

    /**
     * Gets intensity.
     *
     * @return the intensity
     */
    public java.awt.Color getIntensity() {
        return _intensity.getColor();
    }
}
