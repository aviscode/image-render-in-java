package unittests;

import geometries.*;
import org.junit.Assert;
import org.junit.Test;
import primitives.*;


import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * @author Avi Rosenberg
 */
public class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#getNormal()}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Assert.assertEquals("Bad normal to plain", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal());
    }
}

