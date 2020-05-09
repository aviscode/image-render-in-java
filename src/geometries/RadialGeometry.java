package geometries;

import primitives.Color;

import static primitives.Util.isZero;

/**
 * The type Radial geometry.
 */
public abstract class RadialGeometry extends Geometry {
    private final double _radius;

    /**
     * Instantiates a new Radial geometry.
     *
     * @param emissionLight the emission light
     * @param radius        the radius
     */
    public RadialGeometry(Color emissionLight, double radius) {
        super(emissionLight);
//        if (isZero(radius) || (radius < 0.0))
//            throw new IllegalArgumentException("radius " + radius + " is not valid");
        _radius = radius;
    }

    /**
     * Instantiates a new Radial geometry.
     *
     * @param other the other
     */
    public RadialGeometry(RadialGeometry other){
        super(Color.BLACK);
        _radius= other._radius;
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Radius :(" + _radius + ')';
    }
}
