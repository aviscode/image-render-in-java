package primitives;

import java.util.Objects;

import static primitives.Util.*;

import static java.lang.Math.sqrt;

/**
 * The type Point 3 d.
 */
public class Point3D {
    private Coordinate _x, _y, _z;
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
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
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
        _x = new Coordinate(other.getX());
        _y = new Coordinate(other.getY());
        _z = new Coordinate(other.getZ());
    }

    /**
     * Gets  Coordinate x.
     *
     * @return the x
     */
    public Coordinate getX() {
        return _x;
    }

    /**
     * Gets y.
     *
     * @return the Coordinate y
     */
    public Coordinate getY() {
        return _y;
    }

    /**
     * Gets Coordinate z.
     *
     * @return the z
     */
    public Coordinate getZ() {
        return _z;
    }

    /**
     * Subtract a point from a other point.
     *
     * @param other the other
     * @return the vector
     */
    public Vector subtract(Point3D other) {
        return new Vector(_x.get() - other._x.get(), _y.get() - other._y.get(), _z.get() - other._z.get());
    }

    /**
     * Add a Vector to a point 3D.
     *
     * @param vec the vec
     * @return the new point 3D
     */
    public Point3D add(Vector vec) {
        return new Point3D(_x.get() + vec.getHead()._x.get(), _y.get() + vec.getHead()._y.get(), _z.get() + vec.getHead()._z.get());
    }

    /**
     * Distance squared double.
     *
     * @param other the other
     * @return the squared double as a double
     */
    public double distanceSquared(Point3D other) {
        Vector temp = other.subtract(this);
        double x = temp.getHead()._x.get();
        double y = temp.getHead()._y.get();
        double z = temp.getHead()._z.get();
        return x * x + y * y + z * z;
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
