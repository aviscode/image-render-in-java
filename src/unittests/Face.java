package unittests;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * Testing basic shadows
 *
 * @author Dan
 */
public class Face {

    /**
     * Produce a picture of a sphere and triangle with point light and shade
     */
    @Test
    public void SphereTriangleInitial() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(100);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(15, 15, 15), 0));
        scene.addGeometries(new Plane(new Color(Color.BLACK), new Material(0.2, 0.8, 10, 0.1, 0.9), new Vector(1, 0, 0), new Point3D(0.2, 0, 150)),

                //Lips
                new Triangle(new Color(128, 0, 0), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-15, 43, 150), new Point3D(-35, 43, 150), new Point3D(-7, 35, 150)),
                new Triangle(new Color(165, 42, 42), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-15, 43, 150), new Point3D(0, 43, 150), new Point3D(-7, 35, 150)),
                new Triangle(new Color(165, 42, 42), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 60, 150), new Point3D(0, 45, 150), new Point3D(-10, 60, 150)),
                new Triangle(new Color(128, 0, 0), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-15, 45, 150), new Point3D(0, 45, 150), new Point3D(-10, 60, 150)),
                new Triangle(new Color(165, 42, 42), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-15, 45, 150), new Point3D(-35, 44, 150), new Point3D(-10, 60, 150)),

                // Safam
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-90, -40, 150), new Point3D(-110, -40, 150), new Point3D(-100, -10, 150)),
                new Triangle(new Color(255, 235, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 10, 150), new Point3D(-10, 20, 150), new Point3D(-20, 4, 150)),
                new Triangle(new Color(255, 235, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 10, 150), new Point3D(-10, 20, 150), new Point3D(-20, 4, 150)),
                new Triangle(new Color(255, 235, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 10, 150), new Point3D(-20, -20, 150), new Point3D(-20, 4, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-80, 10, 150), new Point3D(-50, 30, 150), new Point3D(-50, 7, 150)),
                new Triangle(new Color(255, 216, 207), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-80, 10, 150), new Point3D(-80, -20, 150), new Point3D(-50, 7, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-80, 10, 150), new Point3D(-80, -20, 150), new Point3D(-100, -10, 150)),
                new Triangle(new Color(255, 228, 196), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 10, 150), new Point3D(-50, 30, 150), new Point3D(-50, 7, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 10, 150), new Point3D(-50, 30, 150), new Point3D(-39, 43, 150)),
                new Triangle(new Color(255, 235, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, 36, 150), new Point3D(-20, 32, 150), new Point3D(-10, 20, 150)),
                new Triangle(new Color(255, 235, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 10, 150), new Point3D(-20, 30, 150), new Point3D(-39, 43, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 10, 150), new Point3D(-20, 35, 150), new Point3D(-10, 20, 150)),
                new Triangle(new Color(255, 235, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-90, -40, 150), new Point3D(-80, -20, 150), new Point3D(-100, -10, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, 36, 150), new Point3D(-2, 28, 150), new Point3D(-10, 20, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-8, 28, 150), new Point3D(0, 42, 150), new Point3D(0, 15, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-3, 28, 150), new Point3D(0, 43, 150), new Point3D(-10, 35, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-5, 37, 150), new Point3D(-20, 30, 150), new Point3D(-35, 43, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-38, 42, 150), new Point3D(-20, 30, 150), new Point3D(-35, 43, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-1, 30, 150), new Point3D(0, 10, 150), new Point3D(-10, 20, 150)),

                // Eear
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-120, -40, 150), new Point3D(-112, -25, 150), new Point3D(-115, -50, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-100, -20, 150), new Point3D(-112, -25, 150), new Point3D(-115, -50, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-132, -90, 150), new Point3D(-120, -70, 150), new Point3D(-121, -90, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-120, -70, 150), new Point3D(-125, -85, 150), new Point3D(-133, -70, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-120, -70, 150), new Point3D(-126, -70, 150), new Point3D(-126, -52, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-120, -70, 150), new Point3D(-116, -50, 150), new Point3D(-126, -52, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-133, -72, 150), new Point3D(-130, -60, 150), new Point3D(-125, -70, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-125, -50, 150), new Point3D(-130, -60, 150), new Point3D(-125, -70, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-120, -40, 150), new Point3D(-127, -53, 150), new Point3D(-115, -50, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-132, -90, 150), new Point3D(-133, -80, 150), new Point3D(-121, -90, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-133, -70, 150), new Point3D(-133, -80, 150), new Point3D(-121, -90, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-132, -90, 150), new Point3D(-130, -97, 150), new Point3D(-121, -90, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-122, -100, 150), new Point3D(-130, -97, 150), new Point3D(-121, -90, 150)),

                // Hair
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-122, -100, 150), new Point3D(-120, -70, 150), new Point3D(-110, -90, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-122, -100, 150), new Point3D(-110, -120, 150), new Point3D(-110, -90, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-122, -100, 150), new Point3D(-110, -120, 150), new Point3D(-118, -130, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-105, -150, 150), new Point3D(-110, -120, 150), new Point3D(-118, -130, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-105, -150, 150), new Point3D(-110, -170, 150), new Point3D(-118, -130, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-105, -150, 150), new Point3D(-110, -170, 150), new Point3D(-100, -195, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-100, -195, 150), new Point3D(-70, -215, 150), new Point3D(-70, -195, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, -225, 150), new Point3D(-70, -215, 150), new Point3D(-70, -195, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, -225, 150), new Point3D(-20, -235, 150), new Point3D(-20, -225, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -240, 150), new Point3D(-20, -235, 150), new Point3D(-20, -225, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -240, 150), new Point3D(0, -210, 150), new Point3D(-20, -225, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, -225, 150), new Point3D(0, -210, 150), new Point3D(-20, -225, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, -225, 150), new Point3D(0, -210, 150), new Point3D(-70, -195, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -210, 150), new Point3D(0, -170, 150), new Point3D(-40, -210, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, -170, 150), new Point3D(0, -170, 150), new Point3D(-40, -210, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, -170, 150), new Point3D(-80, -195, 150), new Point3D(-40, -210, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, -170, 150), new Point3D(-80, -195, 150), new Point3D(-80, -180, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-100, -195, 150), new Point3D(-80, -195, 150), new Point3D(-80, -180, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-100, -195, 150), new Point3D(-103, -185, 150), new Point3D(-80, -180, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-108, -140, 150), new Point3D(-103, -185, 150), new Point3D(-80, -180, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-80, -180, 150), new Point3D(-110, -140, 150), new Point3D(-110, -90, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-90, -150, 150), new Point3D(-119, -80, 150), new Point3D(-115, -70, 150)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-110, -80, 150), new Point3D(-120, -80, 150), new Point3D(-120, -63, 150)),

                //Eyes
                new Triangle(new Color(255, 255, 255), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, -73, 150), new Point3D(-70, -73, 150), new Point3D(-55, -68, 150)),
                new Triangle(new Color(255, 255, 255), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, -73, 150), new Point3D(-70, -73, 150), new Point3D(-55, -78, 150)),
                new Triangle(new Color(255, 255, 255), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, -73, 150), new Point3D(-40, -73, 150), new Point3D(-55, -68, 150)),
                new Triangle(new Color(255, 255, 255), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, -73, 150), new Point3D(-40, -73, 150), new Point3D(-55, -78, 150)),

                //Pupil
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, -78, 149.9), new Point3D(-62, -73, 149.9), new Point3D(-48, -73, 149.9)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, -68, 149.9), new Point3D(-62, -73, 149.9), new Point3D(-48, -73, 149.9)),

                //Eyebrows
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, -82, 150), new Point3D(-40, -87, 150), new Point3D(-60, -87, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, -87, 150), new Point3D(-55, -82, 150), new Point3D(-65, -82, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, -87, 150), new Point3D(-55, -82, 150), new Point3D(-45, -82, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, -87, 150), new Point3D(-55, -82, 150), new Point3D(-45, -82, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, -87, 150), new Point3D(-70, -87, 150), new Point3D(-65, -82, 150)),

                // Noise
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -80, 150), new Point3D(-10, -80, 150), new Point3D(-3, -60, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-6, -35, 150), new Point3D(-12, -45, 150), new Point3D(-15, -30, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-18, -15, 150), new Point3D(-8, -20, 150), new Point3D(-15, -30, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-6, -37, 150), new Point3D(-8, -20, 150), new Point3D(-15, -30, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, -55, 150), new Point3D(-10, -80, 150), new Point3D(-3, -60, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-4, -50, 150), new Point3D(-10, -55, 150), new Point3D(-12, -45, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-4, -50, 150), new Point3D(-10, -55, 150), new Point3D(-3, -62, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-6, -35, 150), new Point3D(-12, -45, 150), new Point3D(-4, -52, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-20, -15, 150), new Point3D(-20, 0, 150), new Point3D(-10, -5, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-20, 5, 150), new Point3D(-20, 0, 150), new Point3D(-10, -5, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-20, 5, 150), new Point3D(-10, 8, 150), new Point3D(-10, -5, 150)),
                new Triangle(new Color(255, 229, 204), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -40, 150), new Point3D(-8, -40, 150), new Point3D(0, -20, 150)),
                new Triangle(new Color(255, 229, 204), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, -20, 150), new Point3D(-8, -40, 150), new Point3D(0, -20, 150)),
                new Triangle(new Color(255, 229, 204), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, -20, 150), new Point3D(0, 0, 150), new Point3D(0, -20, 150)),
                new Triangle(new Color(255, 229, 204), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 8, 150), new Point3D(-10, 5, 150), new Point3D(0, -20, 150)),
                new Triangle(new Color(255, 229, 204), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, -20, 150), new Point3D(-10, 5, 150), new Point3D(0, -10, 150)),
                new Triangle(new Color(255, 229, 204), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -80, 150), new Point3D(0, -60, 150), new Point3D(-3, -60, 150)),
                new Triangle(new Color(255, 229, 204), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -40, 150), new Point3D(0, -60, 150), new Point3D(-3, -60, 150)),
                new Triangle(new Color(255, 229, 204), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -40, 150), new Point3D(-8, -40, 150), new Point3D(-3, -60, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-5, -5, 150), new Point3D(-10, -5, 150), new Point3D(-10, 15, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -3, 150), new Point3D(-5, -5, 150), new Point3D(0, 9, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, 8, 150), new Point3D(0, 10, 150), new Point3D(0, -5, 150)),
                new Triangle(new Color(255, 178, 102), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, -20, 150), new Point3D(-10, -5, 150), new Point3D(-21, -15, 150)),

                // Metzach
                new Triangle(new Color(255, 205, 148), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -110, 150), new Point3D(-30, -110, 150), new Point3D(-15, -80, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, -110, 150), new Point3D(-30, -88, 150), new Point3D(-15, -81, 150)),
                new Triangle(new Color(255, 205, 148), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, -110, 150), new Point3D(-30, -88, 150), new Point3D(-50, -110, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, -90, 150), new Point3D(-30, -88, 150), new Point3D(-50, -110, 150)),
                new Triangle(new Color(255, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, -90, 150), new Point3D(-70, -100, 150), new Point3D(-50, -110, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, -90, 150), new Point3D(-70, -100, 150), new Point3D(-80, -85, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-71, -97, 150), new Point3D(-80, -85, 150), new Point3D(-100, -90, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-15, -80, 150), new Point3D(-10, -80, 150), new Point3D(-10, -75, 150)),
                new Triangle(new Color(255, 205, 148), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-80, -180, 150), new Point3D(-50, -170, 150), new Point3D(-95, -140, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -170, 150), new Point3D(-30, -150, 150), new Point3D(-50, -170, 150)),
                new Triangle(new Color(255, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-93, -143, 150), new Point3D(-30, -150, 150), new Point3D(-50, -170, 150)),
                new Triangle(new Color(255, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -170, 150), new Point3D(-30, -150, 150), new Point3D(0, -140, 150)),
                new Triangle(new Color(255, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, -150, 150), new Point3D(0, -110, 150), new Point3D(0, -140, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, -150, 150), new Point3D(0, -110, 150), new Point3D(-50, -110, 150)),
                new Triangle(new Color(255, 205, 148), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, -150, 150), new Point3D(-92, -144, 150), new Point3D(-50, -110, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-110, -90, 150), new Point3D(-92, -144, 150), new Point3D(-50, -110, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, -110, 150), new Point3D(0, -80, 150), new Point3D(-15, -80, 150)),
                new Triangle(new Color(255, 205, 148), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-110, -90, 150), new Point3D(-116, -69, 150), new Point3D(-50, -110, 150)),


                //Neck
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-65, 210, 150), new Point3D(-150, 185, 150), new Point3D(-90, 160, 150)),
                new Triangle(new Color(185, 144, 97), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-130, 140, 150), new Point3D(-150, 185, 150), new Point3D(-90, 160, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-130, 140, 150), new Point3D(-115, 140, 150), new Point3D(-90, 160, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, 120, 150), new Point3D(-115, 140, 150), new Point3D(-90, 160, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, 120, 150), new Point3D(-115, 140, 150), new Point3D(-75, 115, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, 120, 150), new Point3D(-65, 100, 150), new Point3D(-75, 115, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, 70, 150), new Point3D(-65, 105, 150), new Point3D(-55, 75, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, 80, 150), new Point3D(-65, 105, 150), new Point3D(-55, 75, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, 80, 150), new Point3D(-30, 95, 150), new Point3D(-25, 85, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-65, 210, 150), new Point3D(-45, 180, 150), new Point3D(-90, 160, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-65, 210, 150), new Point3D(-45, 180, 150), new Point3D(0, 180, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-65, 210, 150), new Point3D(0, 220, 150), new Point3D(0, 180, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-40, 155, 150), new Point3D(-45, 180, 150), new Point3D(-90, 160, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-40, 155, 150), new Point3D(-45, 180, 150), new Point3D(0, 180, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-40, 155, 150), new Point3D(-15, 150, 150), new Point3D(0, 180, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-90, 160, 150), new Point3D(-40, 155, 150), new Point3D(-60, 120, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-45, 125, 150), new Point3D(-40, 155, 150), new Point3D(-60, 120, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-45, 125, 150), new Point3D(-40, 155, 150), new Point3D(-15, 150, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-45, 125, 150), new Point3D(0, 130, 150), new Point3D(-15, 150, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 180, 150), new Point3D(0, 130, 150), new Point3D(-15, 150, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-45, 125, 150), new Point3D(0, 130, 150), new Point3D(-25, 110, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-45, 125, 150), new Point3D(-65, 100, 150), new Point3D(-25, 110, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-45, 125, 150), new Point3D(-65, 100, 150), new Point3D(-60, 125, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 95, 150), new Point3D(-10, 100, 150), new Point3D(-25, 85, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 100, 150), new Point3D(-10, 100, 150), new Point3D(-18, 92, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 130, 150), new Point3D(0, 110, 150), new Point3D(-25, 110, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-65, 100, 150), new Point3D(-58, 110, 150), new Point3D(-50, 70, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, 100, 150), new Point3D(-29, 100, 150), new Point3D(-29, 95, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-40, 96, 150), new Point3D(-50, 80, 150), new Point3D(-56, 90, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 100, 150), new Point3D(-25, 100, 150), new Point3D(-30, 120, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, 90, 150), new Point3D(-60, 105, 150), new Point3D(-25, 110, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-38, 88, 150), new Point3D(-46, 88, 150), new Point3D(-50, 80, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 100, 150), new Point3D(0, 110, 150), new Point3D(-25, 110, 150)),
                new Triangle(new Color(222, 184, 135), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-25, 110, 150), new Point3D(-25, 100, 150), new Point3D(-5, 100, 150)), /////
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 100, 150), new Point3D(0, 95, 150), new Point3D(-18, 92, 150)),
                new Triangle(new Color(174, 143, 96), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, 90, 150), new Point3D(-25, 83, 150), new Point3D(-25, 110, 150)),


                // Face
                new Triangle(new Color(255, 205, 148), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, 70, 150), new Point3D(-80, 50, 150), new Point3D(-50, 85, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, 30, 150), new Point3D(-80, 50, 150), new Point3D(-50, 80, 150)),
                new Triangle(new Color(255, 205, 148), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, 30, 150), new Point3D(-80, 50, 150), new Point3D(-80, 10, 150)),
                new Triangle(new Color(255, 205, 148), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, 30, 150), new Point3D(-33, 45, 150), new Point3D(-50, 80, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-104, -20, 150), new Point3D(-80, 50, 150), new Point3D(-80, 10, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 70, 150), new Point3D(-33, 45, 150), new Point3D(-50, 80, 150)),
                new Triangle(new Color(255, 205, 148), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 70, 150), new Point3D(-33, 45, 150), new Point3D(-25, 60, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, 60, 150), new Point3D(-33, 45, 150), new Point3D(-25, 60, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, 60, 150), new Point3D(-30, 70, 150), new Point3D(-25, 60, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 90, 150), new Point3D(0, 90, 150), new Point3D(0, 95, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 60, 150), new Point3D(0, 85, 150), new Point3D(-7, 60, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 80, 150), new Point3D(-10, 80, 150), new Point3D(-5, 60, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-25, 65, 150), new Point3D(-7, 70, 150), new Point3D(-5, 57, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, 80, 150), new Point3D(-30, 80, 150), new Point3D(-30, 70, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 80, 150), new Point3D(-30, 84, 150), new Point3D(-50, 80, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(0, 80, 150), new Point3D(0, 90, 150), new Point3D(-15, 80, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-20, 90, 150), new Point3D(0, 90, 150), new Point3D(-15, 80, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-40, 70, 150), new Point3D(-10, 80, 150), new Point3D(-23, 65, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-6, 70, 150), new Point3D(-10, 80, 150), new Point3D(-23, 65, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-30, 70, 150), new Point3D(-30, 83, 150), new Point3D(-11, 78, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-45, 80, 150), new Point3D(-15, 93, 150), new Point3D(-5, 70, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-120, -65, 150), new Point3D(-120, -50, 150), new Point3D(-110, -80, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, 8, 150), new Point3D(-30, 10, 150), new Point3D(-40, -30, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-20, -20, 150), new Point3D(-30, 10, 150), new Point3D(-40, -30, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, -50, 150), new Point3D(-40, -30, 150), new Point3D(-18, -10, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, -50, 150), new Point3D(-40, -30, 150), new Point3D(-70, -40, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-20, -45, 150), new Point3D(-85, -60, 150), new Point3D(-70, -40, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-87, -60, 150), new Point3D(-60, -50, 150), new Point3D(-80, -80, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, -75, 150), new Point3D(-20, -50, 150), new Point3D(-10, -50, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-10, -75, 150), new Point3D(-23, -77, 150), new Point3D(-10, -50, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-90, -40, 150), new Point3D(-80, -80, 150), new Point3D(-100, -68, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-85, -60, 150), new Point3D(-90, -40, 150), new Point3D(-80, -20, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-85, -60, 150), new Point3D(-70, -40, 150), new Point3D(-80, -20, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, 8, 150), new Point3D(-70, -40, 150), new Point3D(-80, -20, 150)), /// Pink?
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-50, 8, 150), new Point3D(-70, -40, 150), new Point3D(-40, -30, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-80, -80, 150), new Point3D(-90, -88, 150), new Point3D(-70, -86, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-80, -80, 150), new Point3D(-90, -88, 150), new Point3D(-115, -70, 150)),
                new Triangle(new Color(234, 192, 134), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-80, -80, 150), new Point3D(-118, -55, 150), new Point3D(-115, -70, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-118, -56, 150), new Point3D(-112, -38, 150), new Point3D(-100, -68, 150)),
                new Triangle(new Color(255, 224, 189), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-90, -40, 150), new Point3D(-112, -40, 150), new Point3D(-100, -68, 150))

        );
        //scene.addLights(new SpotLight(new Color(400, 240, 0), new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));
        ImageWriter imageWriter = new ImageWriter("Face image", 500, 500, 2500, 2500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }
}