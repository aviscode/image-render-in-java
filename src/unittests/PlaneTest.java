package unittests;

import geometries.*;
import org.junit.Assert;
import org.junit.Test;
import primitives.*;


import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;
import static primitives.Util.isZero;

/**
 * The type Plane test.
 *
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

    /**
     * Test method for {@Link geometries.Plane#findIntsersections(Primitives.Ray)}
     */
    @Test
    public void FindIntersections(){
        Plane plane=new Plane(new Point3D(0, 0, 1), new Vector(2, 2, 1));

        // ============ EPTts ==============
        //TC01: Ray intersects the plane (1 point)
        assertEquals("Ray intersects Plane",1,plane.findIntsersections(new Ray(new Point3D(0, -1, 0), new Vector(0, 1, 1))).size());

        //TC02: Ray does not intersect the plane(0 point)
        assertNull("Ray doesn't intersects Plane",plane.findIntsersections(new Ray(new Point3D(-1,-1,-1),new Vector(-1,-2,-1))));

        // =============== BVT==================
        //***Group: Ray is parallel to the plane
        //TC03: the ray included in the plane
        assertNull("Ray parallel to the plane and included",plane.findIntsersections(new Ray(new Point3D(4,4,3),new Vector(1,-2,2))));

        //TC04: the ray not included in the plane
        assertNull("Ray parallel to the plane and not included",plane.findIntsersections(new Ray(new Point3D(0,-1,0),new Vector(1,-2,2))));

        //***Group: Ray is orthogonal to the plane
        //TC05: P0 before the plane(1 point)
        assertEquals("Ray is orthogonal to the plane and start before the plane",1,plane.findIntsersections(new Ray(new Point3D(-1,-1,0),plane.getNormal())).size());

        //TC06: P0 in the plane(0 point)
        assertNull("Ray is orthogonal to the plane and start in the plane",plane.findIntsersections(new Ray(new Point3D(4,4,3),plane.getNormal())));

        //TC07: P0 after the plane(0 point)
        assertNull("Ray is orthogonal to the plane and start after the plane",plane.findIntsersections(new Ray(new Point3D(1,1,4),plane.getNormal())));

        //***Group: Ray is neither orthogonal nor parallel to the plane
        // TC08: 𝑃0 is in the plane, but not the ray
        assertNull("Ray is neither orthogonal nor parallel to the plane, p0 in plane",plane.findIntsersections(new Ray(new Point3D(4,4,3),new Vector(1,1,0))));
        //TC09: Ray  begins in the same point which appears as reference point in the plane (Q)
        assertNull("Ray is neither orthogonal nor parallel to the plane, p0 = Q",plane.findIntsersections(new Ray(new Point3D(0,0,1),new Vector(1,1,0))));

    }
}

