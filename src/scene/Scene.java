package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * The type Scene.
 */
public class Scene {
    /**
     * The Name.
     */
    private String _name;
    /**
     * The Background.
     */
    private Color _background;
    /**
     * The Ambient light.
     */
    private AmbientLight _ambientLight;
    /**
     * The Geometries.
     */
    private Geometries _geometries;
    /**
     * The Camera.
     */
    private Camera _camera;
    /**
     * The Distance.
     */
    private double _distance;

    /**
     * Instantiates a new Scene.
     *
     * @param name the name
     */
    public Scene(String name) {
        _name = name;
        _geometries = new Geometries();
    }

    /**
     * Gets ambient light.
     *
     * @return the ambient light
     */
    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    /**
     * Gets camera.
     *
     * @return the camera
     */
    public Camera getCamera() {
        return _camera;
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    public Color getBackground() {
        return _background;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public double getDistance() {
        return _distance;
    }

    /**
     * Gets geometries.
     *
     * @return the geometries
     */
    public Geometries getGeometries() {
        return _geometries;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets ambient light.
     *
     * @param ambientLight the ambient light
     */
    public void setAmbientLight(AmbientLight ambientLight) {
        _ambientLight = ambientLight;
    }

    /**
     * Sets distance.
     *
     * @param distance the distance
     */
    public void setDistance(double distance) {
        _distance = distance;
    }

    /**
     * Sets background.
     *
     * @param background the background
     */
    public void setBackground(Color background) {
       _background = background;
    }

    /**
     * Sets camera.
     *
     * @param camera the camera
     */
    public void setCamera(Camera camera) {
        _camera = camera;
    }

    /**
     * Add geometries.
     *
     * @param geometries the geometries
     */
    public void addGeometries(Intersectable... geometries) {
        _geometries.add(geometries);
    }
}
