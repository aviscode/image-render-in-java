package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.IllegalFormatException;

/**
 * The type Camera.
 */
public class Camera {
    private final Point3D _p0;
    private final Vector _vUp;
    private final Vector _vRight;
    private final Vector _vTo;

    /**
     * Instantiates a new Camera.
     *
     * @param p0 camera location
     * @param vUp camera coordinates in 3D Cartesian coordinate system
     * @param vTo camera coordinates in 3D Cartesian coordinate system
     * @throws IllegalArgumentException for none-orthogonal direction vectors
     */
    public Camera(Point3D p0, Vector vUp, Vector vTo) {
        if (vUp.dotProduct(vTo) != 0)
            throw new IllegalArgumentException("the vectors much be orthogonal");
        _p0 = p0;
        _vUp = vUp.normalized();
        _vTo = vTo.normalized();
        _vRight = vTo.crossProduct(vUp).normalize();
    }

    /**
     * Gets p 0.
     *
     * @return the p 0
     */
    public Point3D get_p0() {
        return _p0;
    }

    /**
     * Gets v up.
     *
     * @return the v up
     */
    public Vector get_vUp() {
        return _vUp;
    }

    /**
     * Gets v right.
     *
     * @return the v right
     */
    public Vector get_vRight() {
        return _vRight;
    }

    /**
     * Gets v to.
     *
     * @return the v to
     */
    public Vector get_vTo() {
        return _vTo;
    }

    /**
     * Construct ray through pixel ray.
     *
     * @param nX             x coordinate of pixel
     * @param nY             y coordinate of pixel
     * @param j              pixel column
     * @param i              pixel row in matrix of view plane
     * @param screenDistance screen distance from camera
     * @param screenWidth    in pixels
     * @param screenHeight   in pixels
     * @return ray constructed from information received
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) {
        double rY = screenHeight / nY, rX = screenWidth / nX;
        Point3D Pc = _p0.add(_vTo.scale(screenDistance));
        double xj = (j - nX / 2.0) * rX + rX / 2.0, yi = (i - nY / 2.0) * rY + rY / 2.0;
        if (xj != 0)
            Pc = Pc.add(_vRight.scale(xj));
        if (yi != 0)
            Pc = Pc.add(_vUp.scale(-yi));
        return new Ray(_p0, Pc.subtract(_p0).normalize());
    }

}
