package primitives;

import java.util.Objects;

import static primitives.Util.*;
import static java.lang.Math.sqrt;

/**
 * The type Vector.
 */
public class Vector {
    private Point3D _head;

    /**
     * Instantiates a new Vector.
     *
     * @param other the other
     */
    public Vector(Vector other) {
        _head = new Point3D(other._head);
    }

    /**
     * Instantiates a new Vector.
     *
     * @param head the head
     */
    public Vector(Point3D head) {
        _head = head;
        if (_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("vector 0 is not valid");

    }

    /**
     * Instantiates a new Vector.
     *
     * @param x the x
     * @param y the y
     * @param z the z
     */
    public Vector(double x, double y, double z) {
        _head = new Point3D(x, y, z);
        if (_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("vector 0 is not valid");
    }

    /**
     * Instantiates a new Vector.
     *
     * @param x the x
     * @param y the y
     * @param z the z
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        _head = new Point3D(x, y, z);
        if (_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("vector 0 is not valid");
    }

    /**
     * Add a Vector To vector.
     *
     * @param other the other
     * @return the vector
     */
    public Vector add(Vector other) {
        return new Vector(_head.add(other));
    }

    /**
     * Subtract vector.
     *
     * @param other the other
     * @return the vector
     */
    public Vector subtract(Vector other) {
        return new Vector(_head.subtract(other._head));
    }

    /**
     * Scale vector by a number.
     *
     * @param num the num
     * @return the scaled vector
     */
    public Vector scale(double num) {
        return new Vector(_head.getX().get() * num, _head.getY().get() * num, _head.getZ().get() * num);
    }

    /**
     * Dot product a vector.
     *
     * @param other the other
     * @return the double of a Dot product  vector.
     */
    public double dotProduct(Vector other) {
        return _head.getX().get() * other._head.getX().get() +
                _head.getY().get() * other._head.getY().get() +
                _head.getZ().get() * other._head.getZ().get();
    }

    /**
     * Cross product vector.
     *
     * @param v2 the other
     * @return the vector
     */
    public Vector crossProduct(Vector v2) {

        return new Vector(_head.getY().get() * v2._head.getZ().get() - _head.getZ().get() * v2._head.getY().get(),
                _head.getZ().get() * v2._head.getX().get() - _head.getX().get() * v2._head.getZ().get(),
                _head.getX().get() * v2._head.getY().get() - _head.getY().get() * v2._head.getX().get());
    }

    /**
     * Length squared a vector.
     *
     * @return the double
     */
    public double lengthSquared() {
        return (_head.getX().get()) * (_head.getX().get()) + (_head.getY().get()) * (_head.getY().get()) + (_head.getZ().get()) * (_head.getZ().get());
    }

    /**
     * Length double.
     *
     * @return the double
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Normalize vector itself.
     *
     * @return the vector normalize
     */
    public Vector normalize() {
        _head = normalized()._head;
        return this;
    }

    /**
     * Normalized vector .
     *
     * @return the a new normalized vector
     */
    public Vector normalized() {
        double divider = this.length();
        return new Vector(new Point3D(_head.getX().get() / divider,
                _head.getY().get() / divider,
                _head.getZ().get() / divider));
    }

    /**
     * Gets head.
     *
     * @return the head
     */
    public Point3D getHead() {
        return _head;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector vector = (Vector) obj;
        return _head.equals(((Vector) obj)._head);
    }

    @Override
    public String toString() {
        return "Vector : (" + _head + ')';
    }

}
