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
 * Test rendering a basic image from a XML file
 *
 * @author Avi rosenberg.
 */
public class XmlRenderTest {
    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     *
     * @throws IOException                  the io exception
     * @throws SAXException                 the sax exception
     * @throws ParserConfigurationException the parser configuration exception
     */
    @Test
    public void renderFromXmlFile() throws IOException, SAXException, ParserConfigurationException {
        Render renderXml = new RenderFromXml().sceneFromXmlFile("src/basicRenderTestTwoColors.xml");
        renderXml.renderImage();
        renderXml.printGrid(50, java.awt.Color.YELLOW);
        renderXml.writeToImage();
    }
}