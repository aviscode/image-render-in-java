package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.awt.*;
import java.util.Collections;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * The type Render.
 */
public class Render {
    private ImageWriter _imageWriter;
    private Scene _scene;

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
        this(scene);
        this._imageWriter = imageWriter;
    }

    /**
     * Gets scene.
     *
     * @return the scene
     */
    public Scene getScene() {
        return _scene;
    }


    /**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    public void renderImage() {
        java.awt.Color background = _scene.getBackground().getColor();
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        double distance = _scene.getDistance();

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
                ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntsersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(column, row, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(column, row, calcColor(closestPoint).getColor());
                }
            }
        }
    }


    private primitives.Color calcColor(GeoPoint gp) {
        primitives.Color result = new primitives.Color(_scene.getAmbientLight().getIntensity());
        result = result.add(gp.getGeometry().getEmmission());
        Vector v = gp.getPoint().subtract(_scene.getCamera().get_p0()).normalize();
        Vector n = gp.getGeometry().getNormal(gp.getPoint());
        Material material = gp.getGeometry().getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getKd();
        double ks = material.getKs();
        if (_scene.getLightSources() != null) {
            for (LightSource lightSource : _scene.getLightSources()) {

                Vector l = lightSource.getL(gp.getPoint());
                double nl = alignZero(n.dotProduct(l));
                double nv = alignZero(n.dotProduct(v));

                if (sign(nl) == sign(nv)) {
                    primitives.Color ip = lightSource.getIntensity(gp.getPoint());
                    result = result.add(calcDiffusive(kd, nl, ip), calcSpecular(ks, l, n, nl, v, nShininess, ip));
                }
            }
        }

        return result;
    }

    private boolean sign(double val) {
        return (val > 0d);
    }

    /**
     * Calculate Specular component of light reflection.
     *
     * @param ks         specular component coef
     * @param l          direction from light to point
     * @param n          normal to surface at the point
     * @param nl         dot-product n*l
     * @param v          direction from point of view to point
     * @param nShininess shininess level
     * @param ip         light intensity at the point
     * @return specular component light effect at the point
     * @author Dan Zilberstein
     */
    private primitives.Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, primitives.Color ip) {
        Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0) return primitives.Color.BLACK; // view from direction opposite to r vector
        return ip.scale(ks * Math.pow(minusVR, nShininess));
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component coef
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     * @author Dan Zilberstein
     */
    private primitives.Color calcDiffusive(double kd, double nl, primitives.Color ip) {
        if (nl < 0) nl = -nl;
        return ip.scale(nl * kd);
    }

    /**
     * Get closest point point 3 d.
     *
     * @param points the points
     * @return the point 3 d
     */
    private GeoPoint getClosestPoint(List<GeoPoint> points) {
        GeoPoint result = null;
        double mindist = Double.MAX_VALUE;
        Point3D p0 = this._scene.getCamera().get_p0();
        for (GeoPoint pt : points) {
            double distance = p0.distance(pt._point);
            if (distance < mindist) {
                mindist = distance;
                result = pt;
            }
        }
        return result;
    }

    /**
     * Print grid.
     *
     * @param interval the interval
     * @param color    the color
     */
    public void printGrid(int interval, primitives.Color color) {
        double rows = this._imageWriter.getNx();
        double collumns = _imageWriter.getNy();
        //Writing the lines.
        for (int col = 0; col < collumns; col++) {
            for (int row = 0; row < rows; row++) {
                if (col % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(row, col, color.getColor());
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
