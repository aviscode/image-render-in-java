package unittests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static primitives.Util.isZero;

import geometries.*;
import org.junit.Test;
import primitives.*;


/**
 * @author Avi Rosenberg
 *
 */
class TubeTest {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Tube tube = new Tube(new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 0)), 1);
		assertEquals("Normal function error", tube.getNormal(new Point3D(1, 2, 0)),  new Vector(1, 0, 0));
	}

}
