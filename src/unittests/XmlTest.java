package unittests;

import elements.*;
import geometries.*;
import org.junit.Test;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import primitives.*;
import renderer.*;
import scene.Scene;

import javax.xml.parsers.*;
import java.io.*;
import java.util.Arrays;

/**
 * Test rendering abasic image from a XML file
 *
 * @author Avi rosenberg.
 */
public class XmlTest {
    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     *
     * @throws ParserConfigurationException the parser configuration exception
     * @throws IOException                  the io exception
     * @throws SAXException                 the sax exception
     */
    @Test
    public void sceneFromXmlFile() throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File("src/basicRenderTestTwoColors.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // ==== Root ====//
        NodeList nList = doc.getElementsByTagName("scene");
        Scene test = null;
        ImageWriter imageWriter = null;
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element scene = (Element) nNode;
                String backgroundColor = scene.getAttribute("background-color");
                test = new Scene(nNode.getNodeName() + " " + (i + 1));
                double[] backgroundColorDoubles = stringToDoubleArr(backgroundColor);
                test.setBackground(new Color(backgroundColorDoubles[0], backgroundColorDoubles[1], backgroundColorDoubles[2]));
                test.setDistance(Float.parseFloat(scene.getAttribute("screen-distance")));

                //==== camera ====//
                Node camera = scene.getElementsByTagName("camera").item(0);
                NamedNodeMap temp = camera.getAttributes();
                double[] p0 = stringToDoubleArr(temp.getNamedItem("P0").getNodeValue());
                double[] Vto = stringToDoubleArr(temp.getNamedItem("Vto").getNodeValue());
                double[] Vup = stringToDoubleArr(temp.getNamedItem("Vup").getNodeValue());
                test.setCamera(new Camera(new Point3D(p0[0], p0[1], p0[2]), new Vector(Vto[0], Vto[1], Vto[2]), new Vector(Vup[0], Vup[1], Vup[2])));

                //==== ambient light ====//
                Node ambientLight = scene.getElementsByTagName("ambient-light").item(0);
                temp = ambientLight.getAttributes();
                double[] aL = stringToDoubleArr(temp.getNamedItem("color").getNodeValue());
                test.setAmbientLight(new AmbientLight(new Color(aL[0], aL[1], aL[2]), 1));

                //===== Image writer =====//
                Node image = scene.getElementsByTagName("image").item(0);
                temp = image.getAttributes();
                int screenWidth = Integer.parseInt(temp.getNamedItem("screen-width").getNodeValue());
                int screenHeight = Integer.parseInt(temp.getNamedItem("screen-height").getNodeValue());
                int Nx = Integer.parseInt(temp.getNamedItem("Nx").getNodeValue());
                int Ny = Integer.parseInt(temp.getNamedItem("Ny").getNodeValue());
                imageWriter = new ImageWriter("XML render test", screenWidth, screenHeight, Nx, Nx);

                //==== Get all spheres geometries From the xml file ====//
                Node geometries = scene.getElementsByTagName("geometries").item(0);
                Element geoElemant = (Element) geometries;
                NodeList sphere = geoElemant.getElementsByTagName("sphere");
                for (int j = 0; j < sphere.getLength(); ++j) {
                    temp = sphere.item(j).getAttributes();
                    double[] center = stringToDoubleArr(temp.getNamedItem("center").getNodeValue());
                    double radius = Float.parseFloat(temp.getNamedItem("radius").getNodeValue());
                    test.addGeometries(new Sphere(new Point3D(center[0], center[1], center[2]), radius));
                }

                //==== Get all triangles geometries From the xml file ====//
                NodeList triangle = geoElemant.getElementsByTagName("triangle");
                for (int j = 0; j < triangle.getLength(); ++j) {
                    temp = triangle.item(j).getAttributes();
                    double[] pO0 = stringToDoubleArr(temp.getNamedItem("p0").getNodeValue());
                    double[] pO1 = stringToDoubleArr(temp.getNamedItem("p1").getNodeValue());
                    double[] pO2 = stringToDoubleArr(temp.getNamedItem("p2").getNodeValue());
                    test.addGeometries(new Triangle(new Point3D(pO0[0], pO0[1], pO0[2]), new Point3D(pO1[0], pO1[1], pO1[2]), new Point3D(pO2[0], pO2[1], pO2[2])));
                }

                //==== Get all triangles planes From the xml file ====//
                NodeList plane = geoElemant.getElementsByTagName("plane");
                for (int j = 0; j < plane.getLength(); ++j) {
                    temp = plane.item(j).getAttributes();
                    double[] pO0 = stringToDoubleArr(temp.getNamedItem("p0").getNodeValue());
                    double[] vec = stringToDoubleArr(temp.getNamedItem("p1").getNodeValue());
                    test.addGeometries(new Plane(new Point3D(pO0[0], pO0[1], pO0[2]), new Vector(vec[0], vec[1], vec[2])));
                }
            }
        }
        Render render = new Render(imageWriter, test);
        render.renderImage();
        render.printGrid(50, java.awt.Color.YELLOW);
        render.writeToImage();
    }

    /**
     * @param str gets a steing ant returning a list of the numbers in the string.
     */
    private double[] stringToDoubleArr(String str) {
        return Arrays.stream(str.split(" ")).mapToDouble(Double::parseDouble).toArray();
    }
}