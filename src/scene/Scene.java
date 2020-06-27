package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Scene.
 */
public class Scene {
    private final String _name;
    private Color _background;
    private AmbientLight _ambientLight;
    private Geometries _geometries;
    private Camera _camera;
    private double _distance;
    /**
     * The Lights.
     */
    List<LightSource> _lights = null;

    /**
     * Instantiates a new Scene.
     *
     * @param name the name
     */
    public Scene(String name) {
        _name = name;
        _geometries = new Geometries();
        _lights = new LinkedList<LightSource>();
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
     * Gets light sources.
     *
     * @return the light sources
     */
    public List<LightSource> getLightSources() {
        return _lights;
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
     * Add lights.
     *
     * @param lights the lights
     */
    public void addLights(LightSource... lights) {
        Collections.addAll(_lights, lights);

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
