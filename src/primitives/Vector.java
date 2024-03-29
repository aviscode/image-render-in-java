package primitives;

import java.util.LinkedList;
import java.util.List;
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
        _head = other._head;
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
        return _head.subtract(other._head);
    }

    /**
     * Scale vector by a number.
     *
     * @param num the num
     * @return the scaled vector
     */
    public Vector scale(double num) {
        return new Vector(_head._x._coord * num, _head._y._coord * num, _head._z._coord * num);
    }

    /**
     * Dot product a vector.
     *
     * @param other the other
     * @return the double of a Dot product  vector.
     */
    public double dotProduct(Vector other) {
        return _head._x._coord * other._head._x._coord +
                _head._y._coord * other._head._y._coord +
                _head._z._coord * other._head._z._coord;
    }

    /**
     * Cross product vector.
     *
     * @param v2 the other
     * @return the vector
     */
    public Vector crossProduct(Vector v2) {
        return new Vector(_head._y._coord * v2._head._z._coord - _head._z._coord * v2._head._y._coord,
                _head._z._coord * v2._head._x._coord - _head._x._coord * v2._head._z._coord,
                _head._x._coord * v2._head._y._coord - _head._y._coord * v2._head._x._coord);
    }

    /**
     * Length squared a vector.
     *
     * @return the double
     */
    public double lengthSquared() {
        double x = _head.getX();
        double y = _head.getY();
        double z = _head.getZ();
        return x * x + y * y + z * z;
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
        return new Vector(new Point3D(_head._x._coord / divider,
                _head._y._coord / divider,
                _head._z._coord / divider));
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

    /**
     * The function calculates a normal vector by changing the order
     * Two given vector coordinates and multiplying one by minus and making the third coordinate zero
     *
     * @return the normal
     */
    public Vector createNormal() {
        double x = _head._x._coord, y = _head._y._coord, z = _head._z._coord, minVal = x > 0 ? x : -x;
        int check = 1;
        if (Math.abs(y) < minVal) {
            minVal = y > 0 ? y : -y;
            ++check;
        }
        if (Math.abs(z) < minVal) {
            minVal = z > 0 ? z : -z;
            ++check;
        }
        if (check == 1) return new Vector(0, -z, y).normalized(); // Two orthogonal
        else if (check == 2) return new Vector(-z, 0, x).normalized();
        else return new Vector(y, -x, 0).normalized();

    }
}
