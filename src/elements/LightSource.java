package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

/**
 * The interface Light source.
 */
public interface LightSource {
    /**
     * Get light source intensity as it reaches a point
     *
     * @param p the lighted point
     * @return intensity intensity
     */
    public Color getIntensity(Point3D p);

    /**
     * Get normalized vector in the direction from light source
     * towards the lighted point*
     *
     * @param p the p
     * @return the l
     */
    public Vector getL(Point3D p);

    /**
     * Gets distance.
     *
     * @param point the point
     * @return Distance of light source from element
     */
    double getDistance(Point3D point);

    /**
     * Sets radius if needed .
     *
     * @param radius the radius
     * @return the LightSource obj after the radius update (build design)
     */
    LightSource setRadius(double radius);

    /**
     * Sets the random points in the list.
     */
    void setPoints(List<Point3D> list);

    /**
     * Gets radius.
     *
     * @return the radius of a light source
     */
    double getRadius();


    /**
     * Gets direction.
     *
     * @return the direction of a light source
     */
    Vector getDirection();

    /**
     * Gets points.
     *
     * @return the list of the random points on the light source.
     */
    public List<Point3D> getPoints();

    /**
     * Gets position of a LightSource.
     *
     * @return the position
     */
    Point3D getPosition();
}
