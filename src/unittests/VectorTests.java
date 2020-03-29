package unittests;

import org.junit.Test;
import primitives.Vector;

import static org.junit.Assert.*;

import static primitives.Util.*;

/**
 * @author Avi Rosenberg
 */
public class VectorTests {

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    public void testAdd() {
        Vector vec1 = new Vector(1, 2, 3);
        Vector vec2 = new Vector(3, 2, 1);
        assertEquals("ERROR: Point + Vector does not work correctly", new Vector(4, 4, 4), vec1.add(vec2));
    }

    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
     */
    @Test
    public void testSubtract() {
        Vector vec1 = new Vector(1, 2, 3);
        Vector vec2 = new Vector(3, 2, 1);
        assertEquals("ERROR: Point - Point does not work correctly", new Vector(-2, 0, 2), vec1.subtract(vec2));
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    public void testScale() {
        Vector vec1 = new Vector(1, 2, 3);
        assertEquals(new Vector(2, 4, 6), vec1.scale(2));
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    public void testDotProduct() {
        Vector vec1 = new Vector(0, 0, 1);
        Vector vec2 = new Vector(1, 1, 0);
        double dot = vec1.dotProduct(vec2);
        assertEquals("Dot product function error", 0, dot, 0);
        vec1 = new Vector(1, 0, 0);
        vec2 = new Vector(1, 1, 0);
        dot = vec1.dotProduct(vec2);
        assertEquals("Dot product function error", 1, dot, 0);
        vec1 = new Vector(1, 0, 0);
        vec2 = new Vector(5, 0, 0);
        dot = vec1.dotProduct(vec2);
        assertEquals("Dot product function error", 5, dot, 0);
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }

    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
//    @Test
//    public void testLengthSquared() {
//        fail("Not yet implemented");
//    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    public void testLength() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        assertTrue("ERROR: lengthSquared() wrong value",isZero(v1.lengthSquared() - 14));
        assertTrue("ERROR: length() wrong value",isZero(new Vector(0, 3, 4).length() - 5));
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    public void testNormalize() {
        Vector vec1 = new Vector(1, 1, 1);
        assertEquals(new Vector(Math.sqrt(1.0 / 3), Math.sqrt(1.0 / 3), Math.sqrt(1.0 / 3)), vec1.normalize());
    }

    /**
     * Test method for {@link primitives.Vector#normalized()}.
     */
    @Test
    public void testNormalized() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalized();
        assertFalse("ERROR: normalized() function does not create a new vector", u == v);
        assertTrue("ERROR: normalize() result is not a unit vector",isZero(u.length() - 1));
    }

}

