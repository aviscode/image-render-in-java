package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The type Directional light.
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector _direction;

    /**
     * Instantiates a new Directional light.
     *
     * @param intensity the intensity
     * @param direction the direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
