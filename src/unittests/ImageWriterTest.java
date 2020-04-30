package unittests;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import renderer.ImageWriter;

import java.awt.*;

/**
 * @author Avi Rosenberg
 */
public class ImageWriterTest {
    /**
     * Test method for {@link Cylinder#getNormal(Point3D)}.
     */
    @Test
    public void testBuildImage() {
        ImageWriter im = new ImageWriter("testImage", 1600, 1000, 800, 500);
        for (int row = 0; row < 500; row++) {
            for (int col = 0; col < 800; col++) {
                if (col % 50 == 0 || row % 50 == 0) {
                    im.writePixel(col, row, Color.WHITE);
                } else {
                    im.writePixel(col, row, Color.BLUE);
                }
            }
        }
        im.writeToImage();
    }
}