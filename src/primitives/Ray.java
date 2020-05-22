package primitives;

import static primitives.Util.isZero;

/**
 * The type Ray.
 */
public class Ray {
    private static final double DELTA = 0.1;
    private Point3D _point;
    private Vector _direction;

    public Ray(Point3D poo, Vector direction, Vector n) {
        Vector delta = n.scale(n.dotProduct(direction) > 0 ? DELTA : -DELTA);
        _point = new Point3D(poo.add(delta));
        _direction = direction.normalized();
    }
    /**
     * Instantiates a new Ray.
     *
     * @param other     the point
     * @param direction the direction
     */
    public Ray(Point3D other, Vector direction) {
        _point =other;
        _direction = direction.normalized();
    }

    public Ray(Ray other) {
        _point = new Point3D(other._point);
        _direction = other._direction.normalized();
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

    /**
     * Gets target point.
     *
     * @param length the length.
     * @return the new Point3D.
     */
    public Point3D getTargetPoint(double length) {
        return isZero(length) ? _point : _point.add(_direction.scale(length));
    }

    @Override
    public String toString() {
        return "Ray :" + _point + "  " + _direction;
    }
}
