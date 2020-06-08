package primitives;

import elements.LightSource;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The type Ray.
 */
public class Ray {
    private static final double DELTA = 0.1;
    private Point3D _point;
    private Vector _direction;

    /**
     * Instantiates a new Ray and moves the point by 0.1 in the normal direction
     *
     * @param poo       the poo
     * @param direction the direction
     * @param n         the n
     */
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
        _point = other;
        _direction = direction.normalized();
    }

    /**
     * Instantiates a new Ray.
     *
     * @param other the other
     */
    public Ray(Ray other) {
        _point = new Point3D(other._point);
        _direction = other._direction.normalized();
    }

    /**
     * Gets p.
     *
     * @return the point
     */
    public Point3D getP() {
        return _point;
    }

    /**
     * Gets direction.
     *
     * @return the vector direction
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

    /**
     * Create beam rays list.
     *
     * @param ls      the LightSource
     * @param point   the point i want to calculate its color
     * @param normal  the normal to the point
     * @param numRays the number of rays in the beam
     * @return the list
     */
    public List<Ray> createRaysBeam(LightSource ls, Point3D point, Vector normal, int numRays) {
        List<Ray> rayList = new LinkedList<Ray>();
        rayList.add(this);
        List<Point3D> pointList = ls.getPoints();   //in spot light i have already random points
        Vector lightDirection = ls.getDirection();  //only spot light return vector, other return null
        if (pointList == null && ls.getRadius() > 0) {//it is not directional light and it has a radius
            if (lightDirection != null) {//case spot light in first time
                ls.setPoints(createRandomPoints(ls.getPosition(), lightDirection, ls.getRadius(), numRays));
                pointList = ls.getPoints();
            } else//case point light
                pointList = createRandomPoints(ls.getPosition(), _direction.normalize(), ls.getRadius(), numRays);
        }
        if (pointList != null) {
            for (Point3D p : pointList)
                rayList.add(new Ray(point, p.subtract(point).normalize(), normal));
        }
        return rayList;
    }

    /**
     * Create random points surround the point by given normal and radius.
     *
     * @param centerPoint the center point
     * @param direction   the direction
     * @param radius      the radius
     * @param numRays     the num of rays
     * @return the list
     */
    private List<Point3D> createRandomPoints(Point3D centerPoint, Vector direction, double radius, int numRays) {
        List<Point3D> randomPoints = new LinkedList<Point3D>();
        Vector vX = direction.normalize().createNormal();
        Vector vY = vX.crossProduct(direction.normalize());
        double x, y;
        for (int i = 0; i < numRays; i++) {
            x = getRandom(-1, 1);
            y = Math.sqrt(1 - x * x);
            Point3D p = centerPoint;
            x = alignZero(x * (getRandom(-radius, radius)));
            y = alignZero(y * (getRandom(-radius, radius)));
            if (x != 0)
                p = p.add(vX.scale(x));
            if (y != 0)
                p = p.add(vY.scale(y));
            randomPoints.add(p);
        }
        return randomPoints;
    }

    /**
     * return a random number
     *
     * @param min  the min value
     * @param max  the max value
     * @return the random
     */
    private double getRandom(double min, double max) {
        Random random = new Random();
        return random.nextDouble() * (max - min) + min;
    }

    @Override
    public String toString() {
        return "Ray :" + _point + "  " + _direction;
    }
}
