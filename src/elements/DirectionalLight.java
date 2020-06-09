package elements;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static primitives.Util.isZero;

/**
 * The type Directional light.
 */
public class DirectionalLight extends Light implements LightSource {
    private static final int DISTANCE_OF_CONUS = 1;
    private static final int NUM_OF_POINTS = 10;
    private Random rand = new Random();

    private Vector _direction;

    /**
     * Instantiates a new Directional light.
     *
     * @param intensity the intensity
     * @param direction the direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalized();
    }

    @Override
    public Color getIntensity(Point3D p) {
        return _intensity;
    }

    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public LightSource setRadius(double radius) {
        return null;
    }

    @Override
    public void setPoints(List<Point3D> list) {

    }

    @Override
    public double getRadius() {
        return 0;
    }

    @Override
    public Vector getDirection() {
        return _direction;
    }

    @Override
    public List<Point3D> getPoints() {
        return null;
    }

    @Override
    public Point3D getPosition() {
        return null;
    }
}
