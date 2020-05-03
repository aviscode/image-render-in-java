package unittests;

import geometries.Plane;
import org.junit.Assert;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

/**
 * The type Point 3 d test.
 *
 * @author Avi Rosenberg
 */
public class Point3DTest {

    /**
     * Test method for {@link primitives.Point3D#subtract(primitives.Point3D)}.
     */
    @Test
    public void testSubtract() {
        assertEquals("ERROR subtract() in Point3D", new Vector(1, 3, 2), new Point3D(2, 4, 3).subtract(new Point3D(1, 1, 1)));
    }

    /**
     * Test method for {@link primitives.Point3D#add(primitives.Vector)}.
     */
    @Test
    public void testAdd() {
        assertEquals("ERROR add() in Point3D", new Point3D(2, 2, 2), new Point3D(1, 1, 1).add(new Vector(1, 1, 1)));
    }

    /**
     * Test method for {@link primitives.Point3D#distanceSquared(primitives.Point3D)}.
     */
    @Test
    public void testDistanceSquared() {
        assertEquals("ERROR distanceSquared() in Point3D", 12d, new Point3D(2, 2, 2).distanceSquared(new Point3D(0, 0, 0)));
    }

    /**
     * Test method for {@link primitives.Point3D#distance(primitives.Point3D)}.
     */
    @Test
    public void testDistance() {
        assertEquals("ERROR distance() in Point3D", Math.sqrt(12d), new Point3D(2, 2, 2).distance(new Point3D(0, 0, 0)));
    }

}

