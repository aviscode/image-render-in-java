package unittests;

import geometries.Plane;
import geometries.Triangle;
import org.junit.Assert;
import org.junit.Test;
import primitives.*;
import primitives.*;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

/**
 * @author Avi Rosenberg
 */
public class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal()}.
     */
    @Test
    public void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle tr = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Assert.assertEquals("Bad normal to plain", new Vector(sqrt3, sqrt3, sqrt3), tr.getPlane().getNormal());

    }

}

