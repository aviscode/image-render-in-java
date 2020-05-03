package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * The type Render.
 */
public class Render {

    /**
     * The Image writer.
     */
    ImageWriter _imageWriter;

    /**
     * The Scene.
     */
    Scene _scene;

    /**
     * Instantiates a new Render.
     *
     * @param scene the scene
     */
    public Render(Scene scene) {
        _scene = scene;
    }

    /**
     * Instantiates a new Render.
     *
     * @param imageWriter the image writer
     * @param scene       the scene
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene=   scene;
    }

    /**
     * Gets scene.
     *
     * @return the scene
     */
    public Scene get_scene() {
        return _scene;
    }


    /**
     * Render image.
     */
    public void renderImage(){
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera= _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        double  distance = _scene.getDistance();

        //width and height are the number of pixels in the rows
        //and columns of the view plane
        int width = (int) _imageWriter.getWidth();
        int height = (int) _imageWriter.getHeight();

        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        Ray ray;
        for (int row = 0; row < Ny; row++) {
            for (int column = 0; column < Nx; column++) {
                ray = camera.constructRayThroughPixel(Nx, Ny, row, column, distance, width, height);
                List<Point3D> intersectionPoints = _scene.getGeometries().findIntsersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(column, row, background);
                } else {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(column, row, calcColor(closestPoint));
                }
            }
        }
    }

    /**
     * Calc color color.
     *
     * @param p the p
     * @return the color
     */
    public Color calcColor(Point3D p){
        return _scene.getAmbientLight().getIntensity();
    }

    /**
     * Get closest point point 3 d.
     *
     * @param points the points
     * @return the point 3 d
     */
    public Point3D getClosestPoint(List<Point3D> points){
        Point3D result = null;
        double mindist = Double.MAX_VALUE;

        Point3D p0 = this._scene.getCamera().get_p0();

        for (Point3D pt: points ) {
            double distance = p0.distance(pt);
            if (distance < mindist){
                mindist= distance;
                result =pt;
            }
        }
        return  result;
    }

    /**
     * Print grid.
     *
     * @param interval the interval
     * @param color    the color
     */
    public void printGrid(int interval, java.awt.Color color){
        double rows = this._imageWriter.getNx();
        double collumns = _imageWriter.getNy();
        //Writing the lines.
        for (int col = 0; col < collumns; col++) {
            for (int row = 0; row < rows; row++) {
                if (col % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(row, col, color);
                }
            }
        }
    }

    /**
     * Write to image.
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }
}
