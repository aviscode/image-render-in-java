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

    /**
     * taking the geometries list and build from it a new tree of boxes and keep it optimal and assigning it back to geometries
     * so it will run much faster when searching  the Interpretable rays with the pic.
     */
    public void makeTree() {
        double minSizeOfBox = Double.MIN_VALUE;  //setting it at first at the min and raising it by the biggest shape in the geometries list so the min bax will be in size of the biggest shape box.
        for (Intersectable g : ((Geometries) (_geometries.getShapes().get(0))).getShapes())
            if (g.getBoxSize() > minSizeOfBox) minSizeOfBox = g.getBoxSize();
        Geometries temp = buildTreeOfBoxes(_geometries, minSizeOfBox);
        if (temp != null) {
            fillTree(temp);
            _geometries = temp;
        }
    }

    /**
     * fill the tree leaves with the boxes
     *
     * @param g the new tree that wae created  in the makeTree function
     */
    private void fillTree(Geometries g) {
        if (g.getShapes().isEmpty()) {
            List<Intersectable> list = ((Geometries) (_geometries.getShapes().get(0))).getShapes();
            Geometries temp = new Geometries();
            //checking if the box of the shape is intersection with the main tree box and if yes so adding it to the tree root.
            for (Intersectable i : list) {
                if (isBoxesIntersect(g, i)) temp.add(i);
            }
            g.add(temp);
        } else {
            //running on the tree leaves in calling the function recursion to check the same.
            for (Intersectable in : g.getShapes())
                if (in instanceof Geometries) fillTree((Geometries) in);
        }
    }

    /**
     * a recursion function that builds tree boxes
     * the recursion stopping condition is when the minSizeOfBox of the geo is bigger than the comparing boxes.
     *
     * @param geo          the list of the shapes
     * @param minSizeOfBox the biggest box size in the geo list
     * @return a new tree of boxes
     */
    private Geometries buildTreeOfBoxes(Geometries geo, double minSizeOfBox) {
        if (geo.getBoxSize() < minSizeOfBox)
            return null;       //in case of the main box of the geo is smaller then the min box that created for the all tree so it will return null
        double minX = geo.getMinX(), minY = geo.getMinY(), minZ = geo.getMinZ();
        double maxX = geo.getMaxX(), maxY = geo.getMaxY(), maxZ = geo.getMaxZ();
        double halfX = (maxX - minX) / 2, halfY = (maxY - minY) / 2, halfZ = (maxZ - minZ) / 2;
        //creating one big box that will be the main new box of the tree.
        Geometries temp = new Geometries(minX, minY, minZ, maxX, maxY, maxZ);
        //creating 8 small boxes in the big box
        Geometries box1 = new Geometries(minX, minY, minZ, minX + halfX, minY + halfY, minZ + halfZ); // up front left
        Geometries box2 = new Geometries(minX + halfX, minY + halfY, minZ + halfZ, maxX, maxY, maxZ); // down back right
        Geometries box3 = new Geometries(minX, minY, minZ + halfZ, maxX - halfX, maxY - halfY, maxZ); // up back left
        Geometries box4 = new Geometries(minX + halfX, minY, minZ + halfZ, maxX, maxY - halfY, maxZ); // up back right
        Geometries box5 = new Geometries(minX + halfX, minY, minZ, maxX, maxY - halfY, maxZ - halfZ); // up front right
        Geometries box6 = new Geometries(minX, minY + halfY, minZ, minX + halfX, maxY, minZ + halfZ); // down front left
        Geometries box7 = new Geometries(minX + halfX, minY + halfY, minZ, maxX, maxY, maxZ - halfZ);// down front right
        Geometries box8 = new Geometries(minX, minY + halfY, minZ + halfZ, maxX - halfX, maxY, maxZ); // down back left
        // checking for etch box of the 8 boxes  if the geo main box size is smaller the box
        // if yes so call the build empty tree recursion  with the sub small box and the main size box of the geo
        // so we build all tree in of boxes in the box so when we will look for Intersectable ray it wil run much faster
        // and doing so for all 8 sub boxes because when we run recursion we create one new box by the size of the given box
        //and 8 more boxes inside it and lik this for all the recursion until the main geo box will be in the size
        // of on box then the recursion will stop
        if (box1.getBoxSize() > minSizeOfBox) {
            box1 = buildTreeOfBoxes(box1, minSizeOfBox);
            if (box1 != null)
                temp.add(box1);
        }
        if (box2.getBoxSize() >= minSizeOfBox) {
            box2 = buildTreeOfBoxes(box2, minSizeOfBox);
            if (box2 != null) temp.add(box2);
        }
        if (box3.getBoxSize() >= minSizeOfBox) {
            box3 = buildTreeOfBoxes(box3, minSizeOfBox);
            if (box3 != null)
                temp.add(box3);
        }
        if (box4.getBoxSize() >= minSizeOfBox) {
            box4 = buildTreeOfBoxes(box4, minSizeOfBox);
            if (box4 != null) temp.add(box4);
        }
        if (box5.getBoxSize() >= minSizeOfBox) {
            box5 = buildTreeOfBoxes(box5, minSizeOfBox);
            if (box5 != null) temp.add(box5);
        }
        if (box6.getBoxSize() >= minSizeOfBox) {
            box6 = buildTreeOfBoxes(box6, minSizeOfBox);
            if (box6 != null) temp.add(box6);
        }
        if (box7.getBoxSize() >= minSizeOfBox) {
            box7 = buildTreeOfBoxes(box7, minSizeOfBox);
            if (box7 != null) temp.add(box7);
        }
        if (box8.getBoxSize() >= minSizeOfBox) {
            box8 = buildTreeOfBoxes(box8, minSizeOfBox);
            if (box8 != null) temp.add(box8);
        }
        // the new main box of the tree with all sub leaves with boxes
        return temp;
    }


    /**
     * check if 2 boxes intersect (8 options to check)
     *
     * @param a the first box
     * @param b the second box
     * @return true if intersect
     */
    private boolean isBoxesIntersect(Geometries a, Intersectable b) {
        double bmaxX = b.getMaxX(), bmaxY = b.getMaxY(), bmaxZ = b.getMaxZ(), bminX = b.getMinX(), bminY = b.getMinY(), bminZ = b.getMinZ();
        if (a.isPointInBox(bmaxX, bmaxY, bmaxZ)) return true;
        if (a.isPointInBox(bminX, bminY, bminZ)) return true;
        if (a.isPointInBox(bminX, bmaxY, bmaxZ)) return true;
        if (a.isPointInBox(bminX, bminY, bmaxZ)) return true;
        if (a.isPointInBox(bmaxX, bminY, bmaxZ)) return true;
        if (a.isPointInBox(bmaxX, bminY, bminZ)) return true;
        if (a.isPointInBox(bmaxX, bmaxY, bminZ)) return true;
        if (a.isPointInBox(bminX, bmaxY, bmaxZ)) return true;
        return false;
    }
}
