package geometries;

import primitives.Color;

/**
 * The type Radial geometry.
 */
public abstract class RadialGeometry extends Geometry {
    private final double _radius;

    /**
     * Instantiates a new Radial geometry.
     *
     * @param num the value of the radius
     */
    public RadialGeometry(double num) {
        _radius = num;
    }

    public RadialGeometry(double radius, Color emmission) {
        super(emmission);
        _radius = radius;
    }

    /**
     * Instantiates a new Radial geometry.
     *
     * @param other the other
     */
    public RadialGeometry(RadialGeometry other) {
        super((other._emmission));
        _radius = other.getRadius();
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
