package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

/**
 * The type Point light.
 */
public class PointLight extends Light implements LightSource {
    protected Point3D _position;
    protected double _kC;
    protected double _kL;
    protected double _kQ;
    protected double _radius;

    /**
     * Instantiates a new Point light.
     *
     * @param intensity the intensity
     * @param position  the position
     * @param kC        the k c
     * @param kL        the k l
     * @param kQ        the k q
     */
    public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
        super(intensity);
        _position = new Point3D(position);
        _kC = kC;
        _kL = kL;
        _kQ = kQ;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = p.distanceSquared(_position);
        double d = p.distance(_position);
        return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));
    }

    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) return null;
        else return p.subtract(_position).normalize();
    }

    @Override
    public double getDistance(Point3D point) {
        return point.distance(_position);
    }

    @Override
    public LightSource setRadius(double radius) {
        _radius = radius;
        return this;
    }

    /**
     * in use for the spot light
     */
    @Override
    public void setPoints(List<Point3D> list) {
    }

    @Override
    public double getRadius() {
        return _radius;
    }

    @Override
    public Vector getDirection() {
        return null;
    }

    @Override
    public List<Point3D> getPoints() {
        return null;
    }

    @Override
    public Point3D getPosition() {
        return _position;
    }
}
