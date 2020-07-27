package renderer;

import elements.*;

import geometries.*;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static primitives.Util.alignZero;
import static primitives.Color.*;
import static primitives.Util.isZero;

/**
 * The type Render.
 */
public class Render {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private ImageWriter _imageWriter;
    private Scene _scene;
    private int _superSampling;
    private int _threads = 1;
    private final int SPARE_THREADS = 2;
    private boolean _print = false;

    /**
     * Pixel is an internal helper class whose objects are associated with a Render object that
     * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
     * its progress.<br/>
     * There is a main follow up object and several secondary objects - one in each thread.
     *
     * @author Dan
     */
    private class Pixel {
        private long _maxRows = 0;
        private long _maxCols = 0;
        private long _pixels = 0;
        public volatile int row = 0;
        public volatile int col = -1;
        private long _counter = 0;
        private int _percents = 0;
        private long _nextCounter = 0;

        /**
         * The constructor for initializing the main follow up Pixel object
         *
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (Render.this._print) System.out.printf("\r %02d%%", _percents);
        }

        /**
         * Default constructor for secondary Pixel objects
         */
        public Pixel() {
        }

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
         * critical section for all the threads, and main Pixel object data is the shared data of this critical
         * section.<br/>
         * The function provides next pixel number each call.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
         * finished, any other value - the progress percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {
            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                // Fix
                target.row = this.row;
                target.col = this.col;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            return -1;
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (percents > 0)
                if (Render.this._print) {
                    System.out.printf("\r %02d%%", percents);
                    System.out.println();
                }
            if (percents >= 0)
                return true;
            if (Render.this._print) {
                System.out.printf("\r %02d%%", 100);
                System.out.println();
            }
            return false;
        }
    }

    /**
     * Set multithreading <br>
     * - if the parameter is 0 - number of coress less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public Render setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
        if (threads != 0)
            _threads = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            if (cores <= 2)
                _threads = 1;
            else
                _threads = cores;
        }
        return this;
    }

    /**
     * Set debug printing on
     *
     * @return the Render object itself
     */
    public Render setDebugPrint() {
        _print = true;
        return this;
    }

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
        final int nX = _imageWriter.getNx();
        final int nY = _imageWriter.getNy();
        final double dist = _scene.getDistance();
        final double width = _imageWriter.getWidth();
        final double height = _imageWriter.getHeight();
        final Camera camera = _scene.getCamera();
        java.awt.Color background = _scene.getBackground().getColor();
        final Pixel thePixel = new Pixel(nY, nX);
        // Generate threads
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) {
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel();
                while (thePixel.nextPixel(pixel)) {
                    Ray ray = camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row, dist, width, height);
                    GeoPoint closestPoint = findClosestIntersection(ray);
                    _imageWriter.writePixel(pixel.col, pixel.row,
                            closestPoint == null ? background : calcColor(closestPoint, ray).getColor());
                }
            });
        }

        // Start threads
        for (Thread thread : threads) thread.start();
        // Wait for all threads to finish
        for (Thread thread : threads)
            try {
                thread.join();
            } catch (Exception e) {
            }
        if (_print) System.out.printf("\r100%%\n");
    }

    private boolean inRange(int num, int min, int max) {
        return (num >= min && num <= max);
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
        double kd = alignZero(material.getKd());
        double ks = alignZero(material.getKs());
        double nv = alignZero(v.dotProduct(n));
        if (nv == 0) return color;
        for (LightSource lightSource : _scene.getLightSources()) {
            Vector l = lightSource.getL(p._point);
            double nl = alignZero(l.dotProduct(n));
            if (nl * nv > 0) {
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
     * Unshaded boolean Checks whether the given area is shaded.
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
     * calculate the reflected rey from element
     *
     * @param n     the normal
     * @param point the point
     * @param inRay the ray sent to the element
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
        Vector v = inRay.getDirection();
        Vector r = v.add(n.scale(-2 * (v.dotProduct(n))));
        return new Ray(point, r, n);
    }

    /**
     * calculate the refracted rey on the element
     *
     * @param n     the normal
     * @param point the point
     * @param inRay the ray sent to the element
     * @return the ray that refracted on the element
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
        return new Ray(point, inRay.getDirection(), n);
    }

    /**
     * Calculate the intersections closest to the beginning of the ray
     *
     * @param ray the ray
     * @return the closest intersection point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersectionsPoints = _scene.getGeometries().findIntsersectionsBound(ray);
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
     * Get closest point to the given point.
     *
     * @param points the points
     * @return the point 3d
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
     * The level of transparency of the element
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
        double ktr, sumKtrAll = 0, distance = ls.getDistance(gp._point);
        List<Ray> beamRays = lightRay.createRaysBeam(ls, gp.getPoint(), n, getSuperSampling());
        for (Ray ray : beamRays) {
            List<GeoPoint> intersections = _scene.getGeometries().findIntsersectionsBound(ray);
            if (intersections == null) {
                sumKtrAll += 1.0;
                continue;
            }
            ktr = 1d;
            for (GeoPoint g : intersections) {
                if (alignZero(g._point.distance(gp._point) - distance) <= 0) {
                    if (ktr < MIN_CALC_COLOR_K) {
                        ktr = 0d;
                        break;
                    }
                    ktr *= g._geometry.getMaterial().getKt();
                }
            }
            sumKtrAll += ktr;
        }
        return sumKtrAll / beamRays.size();
    }

    /**
     * @ returns the superSampling number of rays
     */
    private int getSuperSampling() {
        return _superSampling;
    }

    public Render setSuperSampling(int numRays) {
        _superSampling = numRays;
        return this;
    }
}