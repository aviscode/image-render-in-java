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
                new Triangle(new Color(255,255,255),new Material(0.2, 0.8, 40, 0, 0),new Point3D(-55,-73,150),new Point3D(-70,-73,150),new Point3D(-55,-68,150)),
                new Triangle(new Color(255,255,255),new Material(0.2, 0.8, 40, 0, 0),new Point3D(-55,-73,150),new Point3D(-70,-73,150),new Point3D(-55,-78,150)),
                new Triangle(new Color(255,255,255),new Material(0.2, 0.8, 40, 0, 0),new Point3D(-55,-73,150),new Point3D(-40,-73,150),new Point3D(-55,-68,150)),
                new Triangle(new Color(255,255,255),new Material(0.2, 0.8, 40, 0, 0),new Point3D(-55,-73,150),new Point3D(-40,-73,150),new Point3D(-55,-78,150)),

                //Pupil
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, -78, 149.9), new Point3D(-62, -73, 149.9), new Point3D(-48, -73, 149.9)),
                new Triangle(new Color(89, 60, 31), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, -68, 149.9), new Point3D(-62, -73, 149.9), new Point3D(-48, -73, 149.9)),

                //Eyebrows
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-55, -82, 150), new Point3D(-40, -87, 150), new Point3D(-60, -87, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, -87, 150), new Point3D(-55, -82, 150), new Point3D(-65, -82, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, -87, 150), new Point3D(-55, -82, 150), new Point3D(-45, -82, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, -87, 150), new Point3D(-55, -82, 150), new Point3D(-45, -82, 150)),
                new Triangle(new Color(115, 70, 50), new Material(0.2, 0.8, 40, 0, 0), new Point3D(-60, -87, 150), new Point3D(-70, -87, 150), new Point3D(-65, -82, 150)),

                //Sunglasses





                );
        //scene.addLights(new SpotLight(new Color(400, 240, 0), new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));
        ImageWriter imageWriter = new ImageWriter("Face image", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }
}