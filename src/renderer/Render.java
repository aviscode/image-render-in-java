package renderer;

import elements.*;

import geometries.*;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Color.*;

/**
 * The type Render.
 */
public class Render {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

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
     * Render image by using writePixel method.
     * check each pixel in view plane if intersect some geometries and paint the pixel according that,
     * if not, paint in background's color.
     */
    public void renderImage() {
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        java.awt.Color background = _scene.getBackground().getColor();
        //number of pixels
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        //size of the view plane
        double imageWidth = _imageWriter.getWidth();
        double imageHeight = _imageWriter.getHeight();
        //the distance
        double distance = _scene.getDistance();
        for (int row = 0; row < nY; row++) {
            for (int col = 0; col < nX; col++) {
                Ray ray = camera.constructRayThroughPixel(nX, nY, col, row, distance, imageWidth, imageHeight);
                GeoPoint closestPoint = findClosestIntersection(ray);
                //no intersections - paint the background color
                if (closestPoint == null) {
                    _imageWriter.writePixel(col, row, background);
                } else {//paint in the color of the geometry in the closest point
                    _imageWriter.writePixel(col, row, calcColor(closestPoint, ray).getColor());
                }
            }
        }
    }
    /**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1).add(_scene.getAmbientLight().getIntensity());
    }

    /**
     * Calc color of given point.
     *
     * @param p     the point
     * @param inRay the in ray
     * @param level the level
     * @param k     the k
     * @return the Color
     */
    public Color calcColor(GeoPoint p, Ray inRay, int level, double k) {
        Color color = p._geometry.getEmmission();
        Vector v = inRay.getDirection();
        Vector n = p._geometry.getNormal(p._point);
        Material material = p._geometry.getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getKd();
        double ks = material.getKs();
        double nv = v.dotProduct(n);
        for (LightSource lightSource : _scene.getLightSources()) {
            Vector l = lightSource.getL(p._point);
            double nl = l.dotProduct(n);
            if ((nl > 0d && nv > 0d) || (nl <= 0d && nv <= 0d)) {
                double ktr = transparency(lightSource, l, n, p);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(p._point).scale(ktr);
                    color = color.add(calcDiffusive(kd, nl, lightIntensity), calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
                }
            }
        }
        if (level == 1)
            return Color.BLACK;
        double kr = p._geometry.getMaterial().getKr(), kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(n, p._point, inRay);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null)
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }
        double kt = p._geometry.getMaterial().getKt(), kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(n, p._point, inRay);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null)
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }

    /**
     * Unshaded boolean.
     *
     * @param l  the vector from light source to the point
     * @param n  the normal
     * @param gp the geo point
     * @return the boolean
     */
    private boolean unshaded(Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp._point, lightDirection, n);
        List<GeoPoint> intersections = _scene.getGeometries().findIntsersections(lightRay);
        int count = 0;
        for (GeoPoint g : intersections) {
            if (g._geometry.getMaterial().getKt() > 0)
                count++;
        }
        return count > 0;
    }

    /**
     *
     * @param n  the normal
     * @param point the point
     * @param inRay the ray sent to the element
     * @return the reflected ray from element
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
        Vector v = inRay.getDirection();
        Vector r = v.add(n.scale(-2 * (v.dotProduct(n))));
        return new Ray(point, r, n);
    }

    /**
     *
     * @param n  the normal
     * @param point the point
     * @param inRay the ray sent to the element
     * @return the ray that refracted on the element
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
        return new Ray(point, inRay.getDirection(), n);
    }

    /**
     *Calculate the intersections closest to the beginning of the ray
     *
     * @param ray the ray
     * @return the closest intersection point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersectionsPoints = _scene.getGeometries().findIntsersections(ray);
        if (intersectionsPoints == null) return null;
        Point3D rayStart = ray.getP();
        double min = Double.MAX_VALUE;
        GeoPoint closest = null;
        for (GeoPoint p : intersectionsPoints) {
            double check = rayStart.distance(p._point);
            if (check < min) {
                min = check;
                closest = p;
            }
        }
        return closest;
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
     */
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
        Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0) return Color.BLACK; // view from direction opposite to r vector
        return ip.scale(ks * Math.pow(minusVR, (double) nShininess));
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component coef
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     */
    private Color calcDiffusive(double kd, double nl, Color ip) {
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
        GeoPoint result = new GeoPoint(points.get(0).getGeometry(), points.get(0).getPoint());
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
    public void printGrid(int interval, Color color) {
        double rows = this._imageWriter.getNx();
        double columns = _imageWriter.getNy();
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                if (col % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(row, col, color.getColor());
                }
            }
        }
    }

    /**
     * Write to image.
     * use the imageWriter method to write and save the image.
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    /**
     * double transparency.
     *
     * @param l  the vector from lithe source to the point
     * @param n  the normal
     * @param gp the geo point
     * @param ls the light source
     * @return the transparency
     */
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp._point, lightDirection, n);
        List<GeoPoint> intersections = _scene.getGeometries().findIntsersections(lightRay);
        if (intersections == null) return 1.0;
        double distance = ls.getDistance(gp._point);
        double ktr = 1.0;
        for (GeoPoint g : intersections) {
            if (alignZero(g._point.distance(gp._point) - distance) <= 0) {
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
                ktr *= g._geometry.getMaterial().getKt();
            }
        }
        return ktr;
    }
}