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
        Sphere sph0 = new Sphere(new Point3D(0, 0, 3), 1);
        List<Point3D> results1;
        int count = 0, Nx = 3, Ny = 3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                Ray ray = camera1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3);
                results1 = sph0.findIntsersections(ray);
                if (results1 != null)
                    count += results1.size();
            }
        }
        assertEquals(2, count);

        //TC02: sphere r = 2.5, 18 intersection points
        Sphere sph1 = new Sphere(new Point3D(0, 0, 2.5), 2.5);
        List<Point3D> results2;
        count = 0;
        Nx = 3;
        Ny = 3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results2 = sph1.findIntsersections(camera2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results2 != null)
                    count += results2.size();
            }
        }
        assertEquals(18, count);

        //TC03: sphere r = 2, 10 intersection points
        Sphere sph3 = new Sphere(new Point3D(0, 0, 2), 2);
        List<Point3D> results3;
        count = 0;
        Nx = 3;
        Ny = 3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results3 = sph3.findIntsersections(camera2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results3 != null)
                    count += results3.size();
            }
        }
        assertEquals(10, count);


        // TC04: sphere r = 4, 9 intersection points
        Sphere sph4 = new Sphere(new Point3D(0, 0, 0.5), 4);
        List<Point3D> results4;
        count = 0;
        Nx = 3;
        Ny = 3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results4 = sph4.findIntsersections(camera1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results4 != null)
                    count += results4.size();
            }
        }
        assertEquals(9, count);


        //TC05: sphere r = 0.5, 9 intersection points
        Sphere sph5 = new Sphere(new Point3D(0, 0, -1), 0.5);
        List<Point3D> results5;
        count = 0;
        Nx = 3;
        Ny = 3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results5 = sph5.findIntsersections(camera1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results5 != null)
                    count += results5.size();
            }
        }
        assertEquals(0, count);

    }

    /**
     * Construct ray through pixel with plane.
     */
    @Test
    public void constructRayThroughPixelWithPlane() {

        //TC01 9 intersection points- plane against camera
        Plane p = new Plane(new Point3D(0.0, 0.0, 1.0), new Vector(0.0, 0.0, 5.0));
        List<Point3D> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = p.findIntsersections(camera1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(9, count);

        //TC02 Nine intersection points- plane at angle
        p = new Plane(new Point3D(0.0, 0.0, 1.0), new Vector(0.0, -1.0, 5.0));
        count = 0;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = p.findIntsersections(camera1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(9, count);

        //TC03 6 intersection points- plane at angle
        p = new Plane( new Point3D(0.0, 0.0, 1.0),new Vector(0.0, -10.0, 10.0));
        count = 0;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = p.findIntsersections(camera1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(6, count);
    }

    /**
     * Construct ray through pixel with triangle.
     */
    @Test
    public void constructRayThroughPixelWithTriangle() {
        //TC01 1 intersection point
        Triangle t = new Triangle(new Point3D(0.0, -1.0, 2.0), new Point3D(1.0, 1.0, 2.0), new Point3D(-1.0, 1.0, 2.0));
        List<Point3D> results;
        int count = 0;
        int Nx = 3;
        int Ny = 3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = t.findIntsersections(camera1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(1, count);

        //TC02 2 intersection points
        t = new Triangle(new Point3D(0.0, -20.0, 2.0), new Point3D(1.0, 1.0, 2.0), new Point3D(-1.0, 1.0, 2.0));
        count = 0;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = t.findIntsersections(camera1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(2, count);
    }
}

