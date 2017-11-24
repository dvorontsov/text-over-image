package denv.graphics.textoverimage.service;

import com.google.gson.JsonSyntaxException;
import denv.graphics.textoverimage.dto.ColorRGBA;
import denv.graphics.textoverimage.dto.Gradient;
import denv.graphics.textoverimage.dto.TextOverImageConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.testng.Assert.*;

/**
 * Created by Den on 11/22/2017.
 */
public class TextOverImageServiceImplTest {

    private TextOverImageServiceImpl textOverImageService;

    @BeforeMethod
    public void setUp() throws Exception {
        textOverImageService = new TextOverImageServiceImpl();
    }

    @Test
    public void buildConfigurationFromFile() throws URISyntaxException {
        String configFilePath =  new File(getClass().getResource("/config.json").getPath()).getPath();
        TextOverImageConfiguration result = textOverImageService.buildConfigurationFromFile(configFilePath);

        assertNotNull(result);
        assertEquals(result.getHeight(), 416);
        assertEquals(result.getWidth(), 800);
        assertEquals(result.getImageSrc(), "examples/example-2/background.png");
        assertFalse(result.getImageLayers().isEmpty());
        assertNotNull(result.getColor());
    }

    @Test
    public void buildConfigurationFromFile_BadFileName() throws URISyntaxException {
        TextOverImageConfiguration result = textOverImageService.buildConfigurationFromFile("does_does_exist.json");

        assertNull(result);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void buildConfigurationFromFile_NullInput() throws URISyntaxException {
        TextOverImageConfiguration result = textOverImageService.buildConfigurationFromFile(null);

        assertNull(result);
    }

    @Test
    public void isGradientBackground_True() {
        TextOverImageConfiguration configuration = new TextOverImageConfiguration();
        Gradient gradient = new Gradient(new ColorRGBA(1,1,1,1), new ColorRGBA(255, 255, 255, 1));
        configuration.setGradient(gradient);

        boolean result = textOverImageService.isGradientBackground(configuration);

        assertTrue(result);
    }

    @Test
    public void isGradientBackground_False_NoColorInfo() {
        TextOverImageConfiguration configuration = new TextOverImageConfiguration();
        Gradient gradient = new Gradient();
        configuration.setGradient(gradient);

        boolean result = textOverImageService.isGradientBackground(configuration);

        assertFalse(result);
    }


    @Test
    public void isGradientBackground_False_NoGradientObject() {
        TextOverImageConfiguration configuration = new TextOverImageConfiguration();
        configuration.setGradient(null);

        boolean result = textOverImageService.isGradientBackground(configuration);

        assertFalse(result);
    }

    @Test
    public void isImageBackground_True() {
        TextOverImageConfiguration configuration = new TextOverImageConfiguration();
        configuration.setImageSrc("path_to_img");

        boolean result = textOverImageService.isImageBackground(configuration);

        assertTrue(result);
    }

    @Test
    public void isImageBackground_False() {
        TextOverImageConfiguration configuration = new TextOverImageConfiguration();
        configuration.setImageSrc(null);

        boolean result = textOverImageService.isImageBackground(configuration);

        assertFalse(result);
    }

    @Test
    public void isImageBackground_False_EmptyImageSrc() {
        TextOverImageConfiguration configuration = new TextOverImageConfiguration();
        configuration.setImageSrc("");

        boolean result = textOverImageService.isImageBackground(configuration);

        assertFalse(result);
    }

    @Test
    public void buildConfigurationFromJson() {
        String json = "{\"height\": 416, \"width\": 800, \"imageSrc\" : \"examples/example-2/background.png\", \"imageLayers\": []}";

        TextOverImageConfiguration result = textOverImageService.buildConfigurationFromJson(json);

        assertNotNull(result);
        assertEquals(result.getHeight(), 416);
        assertEquals(result.getWidth(), 800);
        assertEquals(result.getImageSrc(), "examples/example-2/background.png");
        assertTrue(result.getImageLayers().isEmpty());
        assertNotNull(result.getColor());
        assertEquals(result.getColor().getA(), 0);
        assertEquals(result.getColor().getR(), 0);
        assertEquals(result.getColor().getG(), 0);
        assertEquals(result.getColor().getB(), 0);
    }

    @Test
    public void buildConfigurationFromJson_NullInput() {
        TextOverImageConfiguration result = textOverImageService.buildConfigurationFromJson(null);

        assertNotNull(result);
        assertEquals(result.getHeight(), 0);
        assertEquals(result.getWidth(), 0);
        assertNull(result.getImageSrc());
        assertTrue(result.getImageLayers().isEmpty());
        assertNotNull(result.getColor());
        assertEquals(result.getColor().getA(), 0);
        assertEquals(result.getColor().getR(), 0);
        assertEquals(result.getColor().getG(), 0);
        assertEquals(result.getColor().getB(), 0);
    }

    @Test
    public void buildConfigurationFromJson_EmptyInput() {
        TextOverImageConfiguration result = textOverImageService.buildConfigurationFromJson("");

        assertNotNull(result);
        assertEquals(result.getHeight(), 0);
        assertEquals(result.getWidth(), 0);
        assertNull(result.getImageSrc());
        assertTrue(result.getImageLayers().isEmpty());
        assertNotNull(result.getColor());
        assertEquals(result.getColor().getA(), 0);
        assertEquals(result.getColor().getR(), 0);
        assertEquals(result.getColor().getG(), 0);
        assertEquals(result.getColor().getB(), 0);
    }

    @Test(expectedExceptions = JsonSyntaxException.class)
    public void buildConfigurationFromJson_BadInput() {
        TextOverImageConfiguration result = textOverImageService.buildConfigurationFromJson("bad input");
    }
}