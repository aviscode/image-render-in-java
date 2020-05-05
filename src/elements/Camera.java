package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.IllegalFormatException;

/**
 * The type Camera.
 */
public class Camera {

    private  Point3D _p0;
    private  Vector _vUp;
    private  Vector _vRight;
    private  Vector _vTo;

    /**
     * Instantiates a new Camera.
     *
     * @param p0  the p 0
     * @param vTo the v to
     * @param vUp the v up
     * @throws IllegalArgumentException for none-orthogonal direction vectors
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {

        //if the the vectors are not orthogonal, throw exception.
        if (Util.isZero(vTo.dotProduct(vUp))) {
            this._p0 = new Point3D(p0);
            this._vTo = vTo.normalized();
            this._vUp = vUp.normalized();
            _vRight = vTo.crossProduct(vUp).normalized();
        }
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
        Point3D cP = _p0.add(_vTo.scale(screenDistance));
        double xj = (j - nX / 2.0) * rX + rX / 2.0, yi = (i - nY / 2.0) * rY + rY / 2.0;
        if (xj != 0)
           cP = cP.add(_vRight.scale(xj));
        if (yi != 0)
            cP = cP.add(_vUp.scale(-yi));
        return new Ray(_p0, cP.subtract(_p0).normalize());
    }
}
