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
        Tube t = new Tube(new Ray(new Point3D(0d, 0d, 0d), new Vector(0d, 1d, 0d)), 1d);
        assertEquals("ERROR getNormal() in  tube", new Vector(1, 0, 0), t.getNormal(new Point3D(1, 2, 0)));
    }

}

