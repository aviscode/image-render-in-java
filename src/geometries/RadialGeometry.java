package geometries;

import primitives.Color;
import primitives.Material;

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
     * @param material      the material
     * @param radius        the radius
     */
    public RadialGeometry(Color emissionLight, Material material, double radius) {
        super(emissionLight,material);
        _radius = radius;
    }

    /**
     * Instantiates a new Radial geometry.
     *
     * @param emissionLight the emission light
     * @param radius        the radius
     */
    public RadialGeometry(Color emissionLight, double radius) {
        this(emissionLight,new Material(0, 0, 0),radius);
    }

    /**
     * Instantiates a new Radial geometry.
     *
     * @param other the other
     */
    public RadialGeometry(RadialGeometry other) {
        this(Color.BLACK,new Material(0, 0, 0),other._radius);
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
