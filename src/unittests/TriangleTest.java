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
 * The type Triangle test.
 *
 * @author Avi Rosenberg
 */
public class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(Point3D)} ()}.
     */
    @Test
    public void getNormal() {
        // ============ EPT ==============
        // TC01: There is a simple single test here
        Triangle tr = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Assert.assertEquals("Bad normal to plain", new Vector(sqrt3, sqrt3, sqrt3), tr.getPlane().getNormal());
    }

    /**
     * Test method for {@Link Triangle#findIntsersections(Primitives.Ray)}
     */
    @Test
    public void FindIntersections(){
       Triangle triangle = new Triangle(new Point3D(-1, -1, 1), new Point3D(1, 1, 1), new Point3D(2, -2, 1));

        // ============ Equivalence Partitions Tests ==============
        //TC01: Inside triangle(1 point)
        assertEquals("Ray intersects inside triangle",1,triangle.findIntsersections(new Ray(new Point3D(0,-2,0),new Vector(1,1,1))).size());
        //TC02: Outside against edge(0 point)
        assertNull("Ray intersects outside against edge",triangle.findIntsersections(new Ray(new Point3D(2,-2,0),new Vector(1,-1,1))));
        //TC03: Outside against vertex(0 point)
        assertNull("Ray intersects outside against vertex",triangle.findIntsersections(new Ray(new Point3D(0,-2,0),new Vector(2,1,1))));

        // =============== Boundary Values Tests ==================
        //TC04: On edge(1 point)
        assertNull("Ray intersects on triangle edge", triangle.findIntsersections(new Ray(new Point3D(1, -3, -1), new Vector(1, 1, 2))));
        //TC05: In vertex(1 point)
        assertEquals("Ray intersects on triangle vertex",1,triangle.findIntsersections(new Ray(new Point3D(0,-2,0),new Vector(1,1,1))).size());
        //TCO6: On edge's continuation(0 point)
        assertNull("Ray intersects On edge continuation",triangle.findIntsersections(new Ray(new Point3D(-5,-1,0),new Vector(1,1,1))));

    }

}

