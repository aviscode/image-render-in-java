package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * The type Spot light.
 */
public class SpotLight extends PointLight {
    private Vector _direction;
    /**
     * Instantiates a new Spot light.
     *
     * @param direction   the directio
     * @param intensity   the intensit
     * @param kC          the kC
     * @param kL          the kL
     * @param kQ          the  kQ
     * @param position    the  position
     */
    public SpotLight(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        super(intensity, position, kC, kL, kQ);
        _direction = new Vector(direction).normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        //TODO fix extremes values
        Color pointLightIntensity = super.getIntensity(p);
        double projection = _direction.dotProduct(getL(p));
        return pointLightIntensity.scale(Math.max(0, projection));
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

}
