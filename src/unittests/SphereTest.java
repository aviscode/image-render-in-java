package unittests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import geometries.*;
import primitives.*;

/**
 * @author Avi Rosenberg
 */
public class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
		Sphere sphere = new Sphere(new Point3D(0, 0, 0),2);
		assertEquals("Get normal function error", sphere.getNormal( new Point3D(0, 2, 0)),  new Vector(0, 1, 0));
    }
}


