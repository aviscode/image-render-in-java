package elements;

import primitives.Color;

/**
 * @author aviros
 */

public class AmbientLight extends Light {
    /**
     * constructor for Ambient Light that receives two arguments:
     * @param Ia intensity of light
     * @param Ka attenuation factor
     */
    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(Ka));
    }
}