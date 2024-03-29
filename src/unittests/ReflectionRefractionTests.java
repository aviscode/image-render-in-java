package unittests;

import geometries.*;
import org.junit.Test;

import elements.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    //@Test
    public void twoSpheres() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));
        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50, new Point3D(0, 0, 50)),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 25, new Point3D(0, 0, 50)));
        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1, 0.0004, 0.0000006));
        ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene);
        //render.renderImage();
        //render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
   // @Test
    public void twoSpheresOnMirrors() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.addGeometries(
                new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500), new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500), new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));
        scene.addLights(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, 750, 150), new Vector(-1, 1, 4), 1, 0.00001, 0.000005));
        ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
        Render render = new Render(imageWriter, scene);
        //render.renderImage();
        //render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     * producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.addGeometries(
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 60), new Point3D(-90, 45, 20), new Point3D(-50, 90, 20), new Point3D(-50, 45, 135)), //
                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 60), new Point3D(0, 30, 0), new Point3D(80, 60, 135), new Point3D(50, 90, 135)), //
                new Polygon(new Color(900, 0, 0), new Material(0.2, 0.2, 200, 0.4, 0), new Point3D(-50, -20, 20), new Point3D(-50, 20, 135), new Point3D(-70, 20, 135), new Point3D(-70, -20, 20)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), 20, new Point3D(60, -50, 50)),
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.4, 0), 13, new Point3D(0, 0, 30)));


//        Geometries a = new Geometries(new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
//                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)));
//        Geometries b = new Geometries(new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 60), new Point3D(-90, 45, 20), new Point3D(-50, 90, 20), new Point3D(-50, 45, 135)), //
//                new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 60), new Point3D(0, 30, 0), new Point3D(80, 60, 135), new Point3D(50, 90, 135)));
//        Geometries c = new Geometries(new Polygon(new Color(900, 0, 0), new Material(0.2, 0.2, 200, 0.4, 0), new Point3D(-50, -20, 20), new Point3D(-50, 20, 135), new Point3D(-70, 20, 135), new Point3D(-70, -20, 20)),
//                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.4, 0), 13, new Point3D(0, 0, 30)));
//        Geometries d = new Geometries(b, c, new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), 20, new Point3D(60, -50, 50)));
//        Geometries e = new Geometries(d,a);
//        scene.addGeometries(e);
//        scene.getGeometries().createBox();
        scene.addLights(
                new SpotLight(new Color(700, 400, 400), new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7).setRadius(7),
                new SpotLight(new Color(700, 400, 400), new Point3D(-150, 200, 30), new Vector(0, 0, 1), 1, 4E-5, 2E-7).setRadius(7),
                new PointLight(new Color(200, 200, 200), new Point3D(150, 300, 20), 1, 4E-5, 2E-7).setRadius(7));

        ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 1000, 1000);
        Render render = new Render(imageWriter, scene).setSuperSampling(400).setDebugPrint();
        render.renderImage();
        render.writeToImage();
    }

    /**
     * magical room .
     */
    //@Test
    public void magicalRoom() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.addGeometries( //
                new Plane(Color.BLACK, new Material(0.2, 0.2, 60, 0.3, 0.2),
                        new Vector(0, -1, 0), new Point3D(0, 400, 100)),
                new Polygon(Color.BLACK, new Material(0.2, 0.2, 200, 0.5, 0),
                        new Point3D(-1, -300, 500), new Point3D(-1, -140, 500), new Point3D(1, -140, 500), new Point3D(1, -300, 500)),
                new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 200, 0, 0.2), // )
                        80, new Point3D(-1, -120, 500)),
                new Polygon(Color.BLACK, new Material(0.2, 0.2, 200, 0.9, 0),
                        new Point3D(-150, -150, 1999), new Point3D(-150, 200, 1999), new Point3D(150, 200, 1999), new Point3D(150, -150, 1999)),
                new Sphere(new Color(800, 0, 0), new Material(0.5, 0.5, 200, 0.1, 0), // )
                        140, new Point3D(260, 260, 500)),
                new Sphere(new Color(0, 0, 200), new Material(0.25, 0.25, 20, 0, 0.5), // )
                        140, new Point3D(-260, 260, 0)),
                new Sphere(new Color(700, 20, 20), new Material(0.5, 0.5, 200, 0.5, 0), // )
                        100, new Point3D(-300, 300, 1500)),
                new Triangle(new Color(100, 300, 100), new Material(0.5, 0.5, 100, 0.5, 0.5),
                        new Point3D(-100, 400, 150), new Point3D(100, 400, 350), new Point3D(0, 200, 250)));
        scene.addLights(new SpotLight(new Color(java.awt.Color.white), new Point3D(0, 0, -1500), new Vector(0, 0, 1), 1, 4E-5, 2E-7).setRadius(3),
                new PointLight(new Color(java.awt.Color.white), new Point3D(0.001, -100, 499), 1, 4E-5, 2E-7).setRadius(3));
        ImageWriter imageWriter = new ImageWriter("The magical room- soft shadow", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene).setSuperSampling(200);
        //render.renderImage();
        //render.writeToImage();
    }

    //@Test
    public void trianglesTransparentSphere1() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.addGeometries(new Plane(new Color(Color.BLACK), new Material(0.2, 0.8, 10, 0.1, 0.9), new Vector(1, 0, 0), new Point3D(0.2, 0, 150)),
                new Plane(new Color(Color.BLACK), new Material(0.2, 0.8, 10, 0.3, 0.9), new Vector(0, 1, 0), new Point3D(0.2, 0, 150)),
                new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500), new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500), new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));
        scene.addLights(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, 750, 150), new Vector(-1, 1, 4), 1, 0.00001, 0.000005));
        ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored2", 2500, 2500, 500, 500);
        Render render = new Render(imageWriter, scene);
        //render.renderImage();
        //render.writeToImage();
    }

    /**
     * bonus test move camera 2.
     */
    //@Test
    public void bonusTMoveCamera2() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(3100, -3100, -2600), new Vector(-2, 2, 2), new Vector(-1, -2, 1)));
        scene.setDistance(600);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Plane(Color.BLACK, new Material(0.2, 0.1, 60, 0, 0),
                        new Vector(0, -1, 0), new Point3D(0, 400, 100)),
                new Polygon(Color.BLACK, new Material(0.2, 0.2, 200, 0.5, 0),
                        new Point3D(-1, -300, 500), new Point3D(-1, -140, 500), new Point3D(1, -140, 500), new Point3D(1, -300, 500)),
                new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 200, 0, 0.8), // )
                        80, new Point3D(-1, -120, 500)),
                new Polygon(Color.BLACK, new Material(0.2, 0.2, 200, 0.9, 0),
                        new Point3D(-150, -150, 1999), new Point3D(-150, 200, 1999), new Point3D(150, 200, 1999), new Point3D(150, -150, 1999)),
                new Sphere(new Color(800, 0, 0), new Material(0.5, 0.5, 200, 0.2, 0), // )
                        140, new Point3D(300, 260, 600)),
                new Sphere(new Color(0, 0, 200), new Material(0.25, 0.25, 20, 0, 0.25), // )
                        140, new Point3D(-260, 260, 0)),
                new Sphere(new Color(700, 20, 20), new Material(0.5, 0.5, 200, 0, 0), // )
                        100, new Point3D(-600, 300, 1300)),
                new Triangle(new Color(100, 300, 100), new Material(0.5, 0.5, 100, 0.5, 0),
                        new Point3D(-100, 400, 150), new Point3D(100, 400, 350), new Point3D(0, 200, 250)));

        scene.addLights(new SpotLight(new Color(700, 400, 400), new Point3D(0, 0, -1500), new Vector(0, 0, 1), 1, 4E-5, 2E-7).setRadius(7),//no. 1
                new PointLight(new Color(200, 600, 200), new Point3D(0.001, -100, 499), 1, 4E-5, 2E-7).setRadius(7),//no.2
                new PointLight(new Color(200, 200, 600), new Point3D(0.001, -50, 1000), 1, 4E-5, 2E-7).setRadius(7));//no.3

        ImageWriter imageWriter = new ImageWriter("The magical room moving camera to right - soft shadow 2", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene).setSuperSampling(200);

        //render.renderImage();
        //render.writeToImage();
    }
}
