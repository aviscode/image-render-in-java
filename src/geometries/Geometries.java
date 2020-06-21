package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.*;

/**
 * The type Geometries.
 */
public class Geometries extends Intersectable {
    private Intersectable _lastAdded;
    private LinkedList<Intersectable> _shapes;

    /**
     * Instantiates a new Geometries.
     */
    public Geometries() {
        _shapes = new LinkedList<Intersectable>();
    }

    /**
     * Constructor with parameters. empty box
     */
    public Geometries(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        _shapes = new LinkedList<Intersectable>();
        _minX = minX;
        _minY = minY;
        _minZ = minZ;
        _maxX = maxX;
        _maxY = maxY;
        _maxZ = maxZ;
    }

    /**
     * Gets shapes.
     *
     * @return the shapes
     */
    public List<Intersectable> getShapes() {
        return _shapes;
    }

    /**
     * Instantiates a new Geometries.
     *
     * @param geometries the geometries
     */
    public Geometries(Intersectable... geometries) {
        _shapes = new LinkedList<Intersectable>();
        add(geometries);
    }

    /**
     * Add shapes to the geometries list.
     *
     * @param geometries the geometries
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            _shapes.add(geometry);
            _lastAdded = geometry;
            updateBoxSize();
        }
        Collections.addAll(_shapes, geometries);
    }

    /**
     * update box size after every new geometry we add to geometries list.
     */
    protected void updateBoxSize() {
        if (_lastAdded._minX < _minX) _minX = _lastAdded._minX;
        if (_lastAdded._maxX > _maxX) _maxX = _lastAdded._maxX;
        if (_lastAdded._minY < _minY) _minY = _lastAdded._minY;
        if (_lastAdded._maxY > _maxY) _maxY = _lastAdded._maxY;
        if (_lastAdded._minZ < _minZ) _minZ = _lastAdded._minZ;
        if (_lastAdded._maxZ > _maxZ) _maxZ = _lastAdded._maxZ;
    }

    /**
     * checks if a point is in the box
     *
     * @return true it the point is in or on the box
     **/
    public boolean isPointInBox(double x, double y, double z) {
        return (x <= _maxX && x >= _minX && y <= _maxY && y >= _minY && z <= _maxZ && z >= _minZ);
    }

    @Override
    public List<GeoPoint> findIntsersections(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable shape : _shapes) {
            List<GeoPoint> tempIntersections = shape.findIntsersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }

    @Override
    public List<GeoPoint> findIntsersectionsBound(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable shape : _shapes) {
            if (shape.isIntersect(ray)) {
                List<GeoPoint> tempIntersections = shape.findIntsersections(ray);
                if (tempIntersections != null) {
                    if (intersections == null)
                        intersections = new LinkedList<>();
                    intersections.addAll(tempIntersections);
                }
            }
        }
        return intersections;
    }
}