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

//    /**
//     * Calculate list of points around some point  in radius
//     *
//     * @param radius the radius
//     * @return list
//     */
//    public List<Point3D> getListOfRandPoints(double radius) {
//        List<Point3D> list = new LinkedList<Point3D>();
//        Point3D currPoint;
//        Vector dir = _direction.scale(DISTANCE_OF_CONUS); // Put the circle at 0.9 distance
//        Vector ort1, ort2, temp1, temp2, finalDir, tempRayVector;
//        double x = dir.getHead().getX().get(), y = dir.getHead().getY().get(), z = dir.getHead().getZ().get();
//        if (x < y && x < z) ort1 = new Vector(0, -z, y).normalized(); // Two orthogonal
//        else if (y < x && y < z) ort1 = new Vector(z, 0, -x).normalized();
//        else ort1 = new Vector(-y, x, 0).normalized();
//        ort2 = dir.crossProduct(ort1);
//        double rand1, rand2, randRadius;
//        for (int i = 0; i < NUM_OF_POINTS; ++i) {
//            do {
//                rand1 = rand.nextInt(50) * Math.pow(-1, rand.nextInt(2) + 1);
//            } while (isZero(rand1));
//            do {
//                rand2 = rand.nextInt(50) * Math.pow(-1, rand.nextInt(2) + 1);
//            } while (isZero(rand2));
//            temp1 = ort1.scale(rand1); // Scale the orthogonal at rand number
//            temp2 = ort2.scale(rand2);
//            finalDir = (temp1.add(temp2)).normalized();
//            do {
//                randRadius = radius * rand.nextDouble(); // Randomize radius
//            } while (isZero(randRadius));
//            finalDir = finalDir.scale(randRadius);
//            currPoint = (_direction.getHead().add(dir)).add(finalDir); // CurrPoint is random point
//            list.add(currPoint);
//        }
//        return list;
//    }
}
