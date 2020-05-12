package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point3D> _vertices;

    /**
     * Gets plane.
     *
     * @return the plane
     */
    public Plane getPlane() {
        return _plane;
    }

    /**
     * Associated plane in which the polygon lays
     */
    protected Plane _plane;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param emissionLight the light color
     * @param material      the martial
     * @param vertices      list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of                                  vertices:                                  <ul>                                  <li>Less than 3 vertices</li>                                  <li>Consequent vertices are in the same                                  point                                  <li>The vertices are not in the same                                  plane</li>                                  <li>The order of vertices is not according                                  to edge path</li>                                  <li>Three consequent vertices lay in the                                  same line (180&#176; angle between two                                  consequent edges)                                  <li>The polygon is concave (not convex></li>                                  </ul>
     */
    public Polygon(Color emissionLight, Material material, Point3D... vertices) {
        super(emissionLight,material);
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        _vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        _plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3) return; // no need for more tests for a Triangle
        Vector n = _plane.getNormal();
        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    /**
     * Instantiates a new Polygon.
     *
     * @param emissionLight the emission light
     * @param vertices      the vertices
     */
    public Polygon(Color emissionLight, Point3D... vertices) {
        this(emissionLight,new Material(0,0,0),vertices);
    }

    /**
     * Instantiates a new Polygon.
     *
     * @param vertices the vertices
     */
    public Polygon(Point3D... vertices) {
        this(Color.BLACK,new Material(0,0,0),vertices);
    }

    @Override
    public Vector getNormal(Point3D point) {
        return _plane.getNormal();
    }


    @Override
    public List<GeoPoint> findIntsersections(Ray ray) {
        List<GeoPoint> intersection = _plane.findIntsersections(ray);
        if (intersection != null) {
            List<Vector> subtracts = new LinkedList<Vector>();//list for calculations
            for (int i = 0; i < _vertices.size(); i++)
                subtracts.add(_vertices.get(i).subtract(ray.getP()));
            //v*N1. N1 = normalize(v1xv2)
            double normalize = Util.alignZero(ray.getDirection().dotProduct(subtracts.get(0).crossProduct(subtracts.get(1)).normalize()));
            for (int index = 1; index < subtracts.size(); index++) {
                double curNormalize;
                if (index < subtracts.size() - 1)//not last vector in list
                    curNormalize = Util.alignZero(ray.getDirection().dotProduct(subtracts.get(index).crossProduct(subtracts.get(index + 1)).normalize()));
                else
                    curNormalize = Util.alignZero(ray.getDirection().dotProduct((subtracts.get(index).crossProduct(subtracts.get(0))).normalize()));
                if (curNormalize * normalize <= 0)
                    return null;
            }
            intersection.get(0)._geometry = this;
            return intersection;
        }
        return null;//no points
    }
}
