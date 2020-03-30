package unittests;

import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Avi Rosenberg
 */
public class TubeTest {


    /**
     * Test method for {@link geometries.Tube#getNormal(Point3D)}.
     */
    @Test
    public void testGetNormal() {
        Tube t = new Tube( new Ray(new Point3D(0, 1, 2), new Vector(2d, 2d, 2d)),2d);
        Vector v = new Vector(-11d /( 11 * Math.sqrt(3)), -11d / (11 * Math.sqrt(3)), -11d / (11 * Math.sqrt(3)));
        assertEquals("Bad normal to tube", v, t.getNormal(new Point3D(1, 2, 3)));
    }

}

