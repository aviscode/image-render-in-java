package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * The type Tube.
 */
public class Tube extends RadialGeometry {

    private Ray _axisRay;

    /**
     * Instantiates a new Tube.
     *
     * @param emissionLight the emission light
     * @param material      the material
     * @param radius        the radius
     * @param ray           the ray
     */
    public Tube(Color emissionLight, Material material, double radius, Ray ray) {
        super(emissionLight, radius);
        _material = material;
        _axisRay = new Ray(ray);

    }

    public Tube( Ray _ray,double _radius) {
        this(Color.BLACK, new Material(0, 0, 0), _radius, _ray);
    }

    public Tube(Color emissionLight, double _radius, Ray _ray) {
        this(emissionLight, new Material(0, 0, 0), _radius, _ray);
    }


    /**
     * Gets the axis of the tube .
     *
     * @return the ray
     */
    public Ray gatAxisRay() {
        return _axisRay;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Point3D p0 = _axisRay.getP();
        Vector v;
        try {
            v = p.subtract(p0);
        } catch (Exception e) {
            return _axisRay.getDirection();
        }
        double temp = alignZero(_axisRay.getDirection().dotProduct(v));
        if (temp == 0)
            return p.subtract(p0).normalize();
        Point3D p1 = p0.add(_axisRay.getDirection().scale(temp));
        return p.subtract(p1).normalize();
    }

    @Override
    public String toString() {
        return "Tube :" + "axis Ray :(" + _axisRay + ") Radius :(" + this.getRadius() + ')';
    }

    @Override
    public List<GeoPoint> findIntsersections(Ray ray) {
        return null;
    }
}
