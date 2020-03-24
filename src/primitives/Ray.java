package primitives;

/**
 * The type Ray.
 */
public class Ray {
    private Point3D _point;
    private Vector _direction;

    /**
     * Instantiates a new Ray.
     *
     * @param other         the point
     * @param direction the direction
     */
    public Ray(Point3D other , Vector direction) {
        _point = other;
        _direction = direction.normalize();
    }

    /**
     * Gets p.
     *
     * @return the p
     */
    public Point3D getP() {
        return _point;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public Vector getDirection() {
        return _direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return _point.equals(other._point) && (_direction.equals(other._direction));
    }

    @Override
    public String toString() {
        return "Ray :" + _point + "  " + _direction;
    }
}
