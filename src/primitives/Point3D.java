package primitives;

import java.util.Objects;

import static primitives.Util.*;

import static java.lang.Math.sqrt;

/**
 * The type Point 3 d.
 */
public class Point3D {
    final Coordinate _x, _y, _z;
    /**
     * The point ZERO.
     */
    final public static Point3D ZERO = new Point3D(0, 0, 0);

    /**
     * Instantiates a new Point 3 d.
     *
     * @param x the x
     * @param y the y
     * @param z the z
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = x;
        _y = y;
        _z = z;
    }

    /**
     * Instantiates a new Point 3 d.
     *
     * @param x the x
     * @param y the y
     * @param z the z
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     * Instantiates a new Point 3 d.
     *
     * @param other the other
     */
    public Point3D(Point3D other) {
        _x = other._x;
        _y = other._y;
        _z = other._z;
    }

    /**
     * Gets  Coordinate x.
     *
     * @return the x
     */
    public double getX() {
        return _x._coord;
    }

    /**
     * Gets y.
     *
     * @return the Coordinate y
     */
    public double getY() {
        return _y._coord;
    }

    /**
     * Gets Coordinate z.
     *
     * @return the z
     */
    public double getZ() {
        return _z._coord;
    }

    /**
     * Subtract a point from a other point.
     *
     * @param other the other
     * @return the vector
     */
    public Vector subtract(Point3D other) {
        return new Vector(_x._coord - other._x._coord, _y._coord - other._y._coord, _z._coord - other._z._coord);
    }

    /**
     * Add a Vector to a point 3D.
     *
     * @param vec the vec
     * @return the new point 3D
     */
    public Point3D add(Vector vec) {
        Point3D head = vec.getHead();
        return new Point3D(_x._coord + head._x._coord, _y._coord + head._y._coord, _z._coord + head._z._coord);
    }

    /**
     * Distance squared double.
     *
     * @param other the other
     * @return the squared double as a double
     */
    public double distanceSquared(Point3D other) {
        double dx = _x._coord - other._x._coord;
        double dy = _y._coord - other._y._coord;
        double dz = _z._coord - other._z._coord;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the distance as a double
     */
    public double distance(Point3D other) {
        return sqrt(distanceSquared(other));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D other = (Point3D) obj;
        return _x.equals(other._x) && _y.equals(other._y) && _z.equals(other._z);
    }

    @Override
    public String toString() {
        return "Point 3D: (" + _x + "," + _y + "," + _z + ")";
    }
}
