package geometries;

/**
 * The type Radial geometry.
 */
public abstract class RadialGeometry implements Geometry {
    private final double _radius;

    /**
     * Instantiates a new Radial geometry.
     *
     * @param num the value of the radius
     */
    public RadialGeometry(double num) {
        _radius = num;
    }

    /**
     * Instantiates a new Radial geometry.
     *
     * @param other the other
     */
    public RadialGeometry(RadialGeometry other) {
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
