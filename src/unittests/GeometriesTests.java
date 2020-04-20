package unittests;

import org.junit.Test;
import primitives.*;
import geometries.*;

import static org.junit.Assert.*;

/**
 * @author Avi Rosenberg
 */
public class GeometriesTests {

    /**
     * Test method for {@link Geometries#findIntsersections(Ray)}
     */
    @Test
    public void findIntsersections() {
        // ============ Boundry Value Analysis ==============
        // TC01: empty list
        Geometries g1 = new Geometries();
        assertNull("Error! Geometries found intersections when there were no shapes", g1.findIntsersections(new Ray(new Point3D(4, 5, 4), new Vector(1, 2, 3))));

        // TC02: no shape hits ray
        Geometries g2 = new Geometries(new Sphere(new Point3D(4, 4, 4), 1), new Plane(new Point3D(0, 6, 0), new Point3D(0, 0, 10), new Point3D(3, 0, 0)), new Polygon(new Point3D(3, 5, 0), new Point3D(9, 11, 0), new Point3D(2, 1, 0), new Point3D(1, 0, 0)), new Triangle(new Point3D(1, 3, 5), new Point3D(1, 1, 1), new Point3D(2, -5, 3)));
        assertNull("Error! Geometries found points when there were none", g2.findIntsersections(new Ray(new Point3D(-4, -3, -2), new Vector(0, 0, -1))));

        //TCO3: one shape hits ray
        g2.add(new Sphere(new Point3D(-4, -3, -15), 15));
        try {
            assertEquals("Error! Geometries found invalid amount of intersections", g2.findIntsersections(new Ray(new Point3D(-4, -3, -2), new Vector(0, 0, -1))).size(), 1);
        } catch (NullPointerException e) {
            fail("Error! Geometries didn't find the one intersection point");
        }

        //TCO4: all shapes hit ray
        Geometries g4 = new Geometries(new Sphere(new Point3D(0, 0, 4), 1), new Plane(new Point3D(0, 0, 1), new Vector(2, 2, 1)),
                new Triangle(new Point3D(-1, -1, 1), new Point3D(1, 1, 1), new Point3D(2, -2, 1)),
                new Polygon(new Point3D(0, 6, 1), new Point3D(-6, 2, 1), new Point3D(-6, 0, 1), new Point3D(-2, -6, 1), new Point3D(4, 2, 1)));
        try {
            assertEquals("Error! Geometries didn't find all intersections", g4.getShapes().size(), g4.findIntsersections(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1))).size());
        } catch (NullPointerException e)//intersection list was null
        {
            fail("Error! Geometries found no intersections when all intersect");
        }

        //TCO5: ray hits all shapes but one
        g4.add(new Sphere(new Point3D(-4, -3, -15), 1));
        assertEquals("Error! Geometries found invalid amount of intersections", g4.getShapes().size() - 1, g4.findIntsersections(new Ray(new Point3D(0, 0, -2), new Vector(0, 0, 1))).size());

        // ============ Equivalence Partitions Tests ==============
        //TCO6: some shapes hit ray and some don't
        g4.add(new Sphere(new Point3D(15, 6, 50), 1));
        try {
            assertEquals("Error! Geometries found invalid amount of intersections", g4.getShapes().size() - 2, g4.findIntsersections(new Ray(new Point3D(0, 0, -2), new Vector(0, 0, 1))).size());
        } catch (NullPointerException e)//intersection list was null
        {
            fail("Error! Geometries found invalid amount of intersections");
        }


    }
}


