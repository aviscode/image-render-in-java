package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class Render {
    private ImageWriter _imageWriter;
    private Scene _scene;

    public Render(ImageWriter imageWriter, Scene scene) {
        _imageWriter = imageWriter;
        _scene = scene;
    }

    public void renderImage() {

    }

    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    public void renderImageSuperSampling() {

    }

    public Color calcColor(Point3D p) {

    }

    public Point3D getClosestPoint(List<Point3D> points) {

    }

    public void printGrid(int interval, java.awt.Color color) {
        for (int i = 1; i < _imageWriter.getNy(); i++) {
            for (int j = 1; j < _imageWriter.getNx(); j++)
                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(j, i, color);
        }
    }
}
