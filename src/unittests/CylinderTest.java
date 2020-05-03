package unittests;

import org.junit.Assert;
import org.junit.Test;
import primitives.*;
import geometries.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * The type Cylinder test.
 *
 * @author Avi Rosenberg
 */
public class CylinderTest {


    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // Test of normal on the tube
        Point3D p = new Point3D(1, 2, 0);
        assertEquals("ERROR in getNormal() Cylinder",
                new Vector(1, 0, 0), new Cylinder(5, 1, new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 0))).getNormal(p));
        //Test normal of tje  base on the Cylinder
        Point3D p0 = new Point3D(0, 0, 0);
        assertEquals("ERROR in getNormal() Cylinder of the base",
                new Vector(0, 1, 0), new Cylinder(5, 1, new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 0))).getNormal(p0));
        //Test  normal of the Top base on the Cylinder
        Point3D p1 = new Point3D(0, 5, 0);
        assertEquals("ERROR in getNormal() Cylinder of the top ",
                new Vector(0, 1, 0), new Cylinder(5, 1, new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 0))).getNormal(p1));
    }

    /**
     * Test find intersections.
     */
    @Test
    public void testFindIntersections() {
        Point3D point1 = new Point3D(0, 0, 0);
        Vector vector1 = new Vector(0, 0, 1);
        Ray ray = new Ray(point1, vector1);
        Cylinder cylinder = new Cylinder(10, 10, ray);
        assertNull(cylinder.findIntsersections(ray));
    }
}

