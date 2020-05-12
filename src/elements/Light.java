package elements;

import primitives.Color;

/**
 * The type Light.
 */
abstract class Light {
    protected Color _intensity;

    /**
     * Instantiates a new Light.
     *TODO
     * @param intensity the intensity
     */
    public Light(Color intensity) {
        _intensity = intensity;
    }

    /**
     * Gets intensity.
     *
     * @return the intensity
     */
    public Color getIntensity() {
        return _intensity;
    }
}
