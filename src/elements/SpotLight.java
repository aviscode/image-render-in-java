package elements;

import static primitives.Util.*;

import primitives.*;

/**
 * The type Spot light.
 */
public class SpotLight extends PointLight {
    private Vector _direction;
    private double _sharpsBeam;

    /**
     * Instantiates a new Spot light.
     *
     * @param colorIntensity the color intensity
     * @param position       the position
     * @param direction      the direction
     * @param kC             the k c
     * @param kL             the k l
     * @param kQ             the k q
     * @param sharpsBeam     the sharps beam
     */
    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ, double sharpsBeam) {
        super(colorIntensity, position, kC, kL, kQ);
        _direction = new Vector(direction).normalized();
        _sharpsBeam = sharpsBeam;
    }

    /**
     * Instantiates a new Spot light.
     *
     * @param colorIntensity the color intensity
     * @param position       the  position
     * @param direction      the directio
     * @param kC             the kC
     * @param kL             the kL
     * @param kQ             the  kQ
     */
    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        this(colorIntensity, position, direction, kC, kL, kQ, 1.0);
    }

    @Override
    public Color getIntensity(Point3D point3D) {
        double dirL = point3D.subtract(_position).normalized().dotProduct(_direction);
        if (alignZero(dirL) <= 0) {
            return Color.BLACK;
        }
        if (_sharpsBeam > 1) {
            dirL = Math.pow(dirL, _sharpsBeam);
        }
        return super.getIntensity(point3D).scale(dirL);
    }
}
