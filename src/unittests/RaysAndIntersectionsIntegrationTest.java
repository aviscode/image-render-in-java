package unittests;

import elements.Camera;
import geometries.*;
import primitives.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing Camera
 *
 * @author Avi Rosenberg
 */
public class RaysAndIntersectionsIntegrationTest {
    /**
     * The Camera.
     */
    Camera camera = new Camera(Point3D.ZERO, new Vector(0, -1, 0), new Vector(0, 0, 1));

    /**
     * Test method for {@link Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     */
    @Test
    public void testSphereIntegration() {
        Sphere sphere = new Sphere(new Point3D(0, 0, 3), 1d);
        // TC01: view plain before sphere (r=1, 2 points)  //constructing rays through all pixels
        int sumPoints = 0, Nx = 3, Ny = 3;
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++) {
                var intersections = sphere.findIntsersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1d, 3d, 3d));//finds ray intersections
                if (intersections != null)
                    sumPoints += intersections.size();
            }
        assertEquals("wrong number of intersection points", 2, sumPoints);

        // TC02: view plain in sphere (r=2.5, 18 points)
        camera = new Camera(new Point3D(0, 0, -0.5), new Vector(0, -1, 0), new Vector(0, 0, 1));
        sphere = new Sphere(new Point3D(0, 0, 2.5), 2.5);
        sumPoints = 0;
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++) {
                var intersections = sphere.findIntsersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));//finds ray intersections
                if (intersections != null)
                    sumPoints += intersections.size();
            }
        assertEquals("wrong number of intersection points", 18, sumPoints);

        // TC03: view plain before sphere (r=2, 10 points)
        sphere = new Sphere(new Point3D(0, 0, 2), 2d);
        sumPoints = 0;
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++) {
                var intersections = sphere.findIntsersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));//finds ray intersections
                if (intersections != null)
                    sumPoints += intersections.size();
            }
        assertEquals("wrong number of intersection points", 10, sumPoints);

        // TC04: view plain before sphere (r=4, 10 points) have to fix it
        sphere = new Sphere(Point3D.ZERO, 4d);
        sumPoints = 0;
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++) {
                var intersections = sphere.findIntsersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));//finds ray intersections
                if (intersections != null)
                    sumPoints += intersections.size();
            }
        assertEquals("wrong number of intersection points", 9, sumPoints);

        // TC05: sphere before camera (r=0.5, 0 points)
        camera = new Camera(Point3D.ZERO, new Vector(0, -1, 0), new Vector(0, 0, 1));
        sphere = new Sphere(new Point3D(0, 0, -1), 0.5);
        sumPoints = 0;
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++) {
                var intersections = sphere.findIntsersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));//finds ray intersections
                if (intersections != null)
                    sumPoints += intersections.size();
            }
        assertEquals("wrong number of intersection points", 0, sumPoints);
    }

    /**
     * Test plane integration.
     */
//plane integration
    @Test
    public void testPlaneIntegration() {
        camera = new Camera(Point3D.ZERO, new Vector(0, -1, 0), new Vector(0, 0, 1));
        Plane plane = new Plane(new Point3D(0, 0, 3), new Vector(0, 0, 1));
        // TC01: plane parallels to camera (9 points)
        int sumPoints = 0, Nx = 3, Ny = 3;
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++) {
                var intersections = plane.findIntsersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));//finds ray intersections
                if (intersections != null)
                    sumPoints += intersections.size();
            }
        assertEquals("wrong number of intersection points", 9, sumPoints);

        // TC02: small angle with view plane (9 points)
        plane = new Plane(new Point3D(0, 0, 3), new Vector(4, -1, -15));
        sumPoints = 0;
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++) {
                var intersections = plane.findIntsersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));//finds ray intersections
                if (intersections != null)
                    sumPoints += intersections.size();
            }
        assertEquals("wrong number of intersection points", 9, sumPoints);

        //TC03: plane has 6 intersection points view plane rays.
        Plane p3 = new Plane(new Point3D(0, 2, 3), new Vector(0, 5, 5));
        sumPoints = 0;//resets sum
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++) {
                var intersections = p3.findIntsersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));//finds ray intersections
                if (intersections != null)
                    sumPoints += intersections.size();
            }
        assertEquals("wrong number of intersection points", 6, sumPoints);
    }

    /**
     * tests triangle and camera ray intersection
     */
    @Test
    public void testTriangleIntegration() {
        camera = new Camera(Point3D.ZERO, new Vector(0, -1, 0), new Vector(0, 0, 1));
        //TCO1: triangle is contained in viewplane and parallels to it (1 intersection)
        Triangle t1 = new Triangle(new Point3D(0, -1, 2), new Point3D(-1, 1, 2), new Point3D(1, 1, 2));
        int Nx = 3, Ny = 3, counter = 0;
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++) {
                var results = t1.findIntsersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));//finds ray intersections
                if (results != null)
                    counter += results.size();
            }
        assertEquals("wrong number of intersections with triangle (case 1)", 1, counter);

        //TCO2: bigger triangle parallels to plane (2 intersection points)
        Triangle t2 = new Triangle(new Point3D(0, -20, 2), new Point3D(-1, 1, 2), new Point3D(1, 1, 2));
        counter = 0;
        for (int i = 0; i < Nx; i++)
            for (int j = 0; j < Ny; j++) {
                var results = t2.findIntsersections(camera.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));//finds ray intersections
                if (results != null)//found intersections
                    counter += results.size();//adds to count
            }
        assertEquals("wrong number of intersections with triangle (case 2)", 2, counter);
    }
}

