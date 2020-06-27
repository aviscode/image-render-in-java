package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.*;

/**
 * The type Geometries.
 */
public class Geometries extends Intersectable {

    private LinkedList<Intersectable> _shapes;

    /**
     * Instantiates a new Geometries.
     */
    public Geometries() {
        _shapes = new LinkedList<Intersectable>();
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
        Collections.addAll(_shapes, geometries);
    }

    @Override
    public void setBox() {
        _minX = Double.MAX_VALUE;
        _minY = Double.MAX_VALUE;
        _minZ = Double.MAX_VALUE;
        _maxX = Double.MIN_VALUE;
        _maxY = Double.MIN_VALUE;
        _maxZ = Double.MIN_VALUE;
        for (Intersectable geo : _shapes) {
            geo.createBox();
            if (geo._boudingVolume) {
                if (geo._minX < _minX) _minX = geo._minX;
                if (geo._maxX > _maxX) _maxX = geo._maxX;
                if (geo._minY < _minY) _minY = geo._minY;
                if (geo._maxY > _maxY) _maxY = geo._maxY;
                if (geo._minZ < _minZ) _minZ = geo._minZ;
                if (geo._maxZ > _maxZ) _maxZ = geo._maxZ;
            }
        }
        _middlePoint = getMiddlePoint();
    }

    @Override
    public List<GeoPoint> findIntsersections(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable shape : _shapes) {
            List<GeoPoint> tempIntersections = shape.findIntsersectionsBound(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }

    /**
     * update box size after every new geometry we add to geometries list.
     */
    protected void updateBoxSize(Intersectable a, Intersectable b) {
        _boudingVolume = true;
        _minX = Double.MAX_VALUE;
        _minY = Double.MAX_VALUE;
        _minZ = Double.MAX_VALUE;
        _maxX = Double.MIN_VALUE;
        _maxY = Double.MIN_VALUE;
        _maxZ = Double.MIN_VALUE;
        _minX = Math.min(a._minX, b._minX);
        _minY = Math.min(a._minY, b._minY);
        _minZ = Math.min(a._minZ, b._minZ);
        _maxX = Math.max(a._maxX, b._maxX);
        _maxY = Math.max(a._maxY, b._maxY);
        _maxZ = Math.max(a._maxZ, b._maxZ);
        _middlePoint = getMiddlePoint();
    }

    /**
     * the function is calling the makeTree function after taking out all infinity shapes to a separate list
     * after the function makeTree was called and return the binary tree list we add the infinity shape list
     * to the head of the binary list so _shapes as all geometries in one list.
     */
    public void callMakeTree() {
        LinkedList<Intersectable> shapesWhitOutBox = null;
        for (int i = 0; i < _shapes.size(); ++i) {
            if (!_shapes.get(i)._finity) {
                if (shapesWhitOutBox == null) shapesWhitOutBox = new LinkedList<Intersectable>();
                shapesWhitOutBox.add(_shapes.remove(i));
            }
        }
        _shapes = makeTree(_shapes);
        _shapes.addAll(0, shapesWhitOutBox);
    }

    /**
     * the function is making pears of two closes geometries until the list is empty
     * the function calls itself until the list contains one geometry node
     *
     * @param shapes the list of finite shapes
     * @return a list of shapes in a binary way
     */
    LinkedList<Intersectable> makeTree(LinkedList<Intersectable> shapes) {
        if (shapes.size() == 1) return shapes;
        LinkedList<Intersectable> _newShapes = null;
        while (!shapes.isEmpty()) {
            Intersectable first = shapes.remove(0), nextTo = shapes.get(0);
            double minD = first._middlePoint.distance(nextTo._middlePoint);
            int min = 0;
            for (int i = 1; i < shapes.size(); ++i) {
                if (minD == 0) break;
                double temp = first._middlePoint.distance(shapes.get(i)._middlePoint);
                if (temp < minD) {
                    minD = temp;
                    nextTo = shapes.get(i);
                    min = i;
                }
            }
            System.out.println(minD);
            if (_newShapes == null)
                _newShapes = new LinkedList<Intersectable>();
            shapes.remove(min);
            Geometries newGeo = new Geometries(first, nextTo);
            newGeo.updateBoxSize(first, nextTo);
            _newShapes.add(newGeo);
            if (shapes.size() == 1) _newShapes.add(shapes.remove(0));
        }
        return makeTree(_newShapes);
    }
}