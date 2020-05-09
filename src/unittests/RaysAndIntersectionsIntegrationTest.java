package unittests;

import elements.Camera;
import geometries.*;
import primitives.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Testing Camera
 *
 * @author Avi Rosenberg
 */
public class RaysAndIntersectionsIntegrationTest {

    /**
     * The Shapes.
     */
    Geometries shapes = new Geometries();
    /**
     * The Camera 1.
     */
    Camera camera1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
    /**
     * The Camera 2.
     */
    Camera camera2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));

    /**
     * Construct ray through pixel with sphere.
     */
    @Test
    public void constructRayThroughPixelWithSphere() {
        // TC01: sphere r = 1, 2 intersection points
        assertEquals(2, numConstructRayThroughPixelWithShape(3, 3, shapes = new Geometries(new Sphere(new Point3D(0, 0, 3), 1)), camera1));

        //TC02: sphere r = 2.5, 18 intersection points
        assertEquals(18, numConstructRayThroughPixelWithShape(3, 3, shapes = new Geometries(new Sphere(new Point3D(0, 0, 2.5), 2.5)), camera2));

        //TC03: sphere r = 2, 10 intersection points
        assertEquals(10, numConstructRayThroughPixelWithShape(3, 3, shapes = new Geometries(new Sphere(new Point3D(0, 0, 2), 2)), camera2));

        // TC04: sphere r = 4, 9 intersection points
        assertEquals(9, numConstructRayThroughPixelWithShape(3, 3, shapes = new Geometries(new Sphere(new Point3D(0, 0, 0.5), 4)), camera1));

        //TC05: sphere r = 0.5, 9 intersection points
        assertEquals(0, numConstructRayThroughPixelWithShape(3, 3, shapes = new Geometries(new Sphere(new Point3D(0, 0, -1), 0.5)), camera1));
    }

    /**
     * Construct ray through pixel with plane.
     */
    @Test
    public void constructRayThroughPixelWithPlane() {
        //TC01 9 intersection points- plane against camera
        assertEquals(9, numConstructRayThroughPixelWithShape(3, 3, shapes = new Geometries(new Plane(new Point3D(0.0, 0.0, 1.0), new Vector(0.0, 0.0, 5.0))), camera1));

        //TC02 Nine intersection points- plane at angle
        assertEquals(9, numConstructRayThroughPixelWithShape(3, 3, shapes = new Geometries(new Plane(new Point3D(0.0, 0.0, 1.0), new Vector(0.0, -1.0, 5.0))), camera1));

        //TC03 6 intersection points- plane at angle
        assertEquals(6, numConstructRayThroughPixelWithShape(3, 3, shapes = new Geometries(new Plane(new Point3D(0.0, 0.0, 1.0), new Vector(0.0, -10.0, 10.0))), camera1));
    }

    /**
     * Construct ray through pixel with triangle.
     */
    @Test
    public void constructRayThroughPixelWithTriangle() {
        //TC01 1 intersection point
        assertEquals(1, numConstructRayThroughPixelWithShape(3, 3, shapes = new Geometries(new Triangle(new Point3D(0.0, -1.0, 2.0), new Point3D(1.0, 1.0, 2.0), new Point3D(-1.0, 1.0, 2.0))), camera1));

        //TC02 2 intersection points
        assertEquals(2, numConstructRayThroughPixelWithShape(3, 3, shapes = new Geometries(new Triangle(new Point3D(0.0, -20.0, 2.0), new Point3D(1.0, 1.0, 2.0), new Point3D(-1.0, 1.0, 2.0))), camera1));
        ;
    }

    private int numConstructRayThroughPixelWithShape(int nX, int nY, Geometries shape, Camera camera) {
        List<Intersectable.GeoPoint> results;
        int count = 0;
        for (int i = 0; i < nX; ++i)
            for (int j = 0; j < nY; ++j) {
                results = shape.findIntsersections(camera.constructRayThroughPixel(nX, nY, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        return count;
    }
}

