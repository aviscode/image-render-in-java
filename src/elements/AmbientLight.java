package elements;

import primitives.Color;

/**
 * The type Ambient light.
 *
 * @author aviros
 */
public class AmbientLight extends Light {
    /**
     * constructor for Ambient Light that receives two arguments:
     *
     * @param iA intensity of light
     * @param kA attenuation factor
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }
}