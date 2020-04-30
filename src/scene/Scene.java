package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

public class Scene {
    private String _name;
    private Color _background;
    private Geometries _geometries;
    private Camera _camera;
    private double _distance;
    private AmbientLight _ambientLight;

    public Scene(String name) {
        _name = name;
        _geometries = new Geometries();
    }

    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public String getName() {
        return _name;
    }

    public Geometries getGeometries() {
        return _geometries;
    }

    public Camera getCamera() {
        return _camera;
    }

    public double getDistance() {
        return _distance;
    }

    public void setBackground(Color background) {
        _background = background;
    }

    public void setCamera(Camera camera) {
        _camera = camera;
    }

    public void setDistance(double distance) {
        _distance = distance;
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        _ambientLight = ambientLight;
    }

    public void addGeometries(Intersectable... geometries) {
        _geometries.add(geometries);
    }
}
