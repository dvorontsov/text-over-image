package denv.graphics.textoverimage.service;

import com.google.gson.Gson;
import denv.graphics.textoverimage.dto.ColorRGBA;
import denv.graphics.textoverimage.dto.ImageLayer;
import denv.graphics.textoverimage.dto.TextOverImageConfiguration;
import denv.graphics.textoverimage.api.TextOverImageService;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * TextOverImageServiceImpl.
 * 
 */
public class TextOverImageServiceImpl implements TextOverImageService {
    
    @Override
    public BufferedImage generateImage(TextOverImageConfiguration configuration) {
        if(configuration == null) {
            return null;
        }
        
        int width = configuration.getWidth();
        int height = configuration.getHeight();
        ColorRGBA backgroundColor = configuration.getColor();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

        WritableRaster writableRaster = image.getRaster();
        
        int arraySize = width * height * 4;
        int[] pixels = new int[arraySize];
        for(int i = 0; i < arraySize; i+=4) {
            // Red channel
            pixels[i] = backgroundColor.getR();
            // Green channel
            pixels[i + 1] = backgroundColor.getG();
            // Blue channel
            pixels[i + 2] = backgroundColor.getB();
            // Alpha channel
            pixels[i + 3] = backgroundColor.getA();
        }
        writableRaster.setPixels(0, 0, width, height, pixels);

        Graphics2D graphics2D = image.createGraphics();
        RenderingHints rn = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, 
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHints(rn);
        
        for(ImageLayer layer : configuration.getImageLayers()) {
            applyLayer(graphics2D, layer);
        }
        graphics2D.dispose();
        
        return image;
    }
    
    @Override
    public boolean writeImage(BufferedImage image, String formatName, String path) {
        try {
            return ImageIO.write(image, formatName, new File(path));
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String configurationFileToString(String filePath) {
        String result = null;
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(filePath));
            result = new String(encoded, Charset.defaultCharset());
        } catch (IOException e) {
            result = null;
        }
        return result; 
    }

    @Override
    public TextOverImageConfiguration buildConfigurationFromFile(String filePath) {
        String json =  this.configurationFileToString(filePath);
        TextOverImageConfiguration configuration = null;
        if(json != null) {
            configuration = this.buildConfigurationFromJson(json);
        }
        return configuration;
    }

    @Override
    public TextOverImageConfiguration buildConfigurationFromJson(String json) {
        if(json == null || json.isEmpty()) {
            return new TextOverImageConfiguration();
        }
        Gson gson = new Gson();
        return gson.fromJson(json, TextOverImageConfiguration.class);
    }

    void applyLayer(Graphics2D graphics2D, ImageLayer layer) {
        int x = layer.getxCoordinate();
        int y = layer.getyCoordinate();
        Color color = new Color(layer.getColor().getR(), layer.getColor().getG(), layer.getColor().getB(), 
                layer.getColor().getA());
        String text = layer.getText();
        String fontName = layer.getFontName();
        int fontStyle = layer.getFontStyle();
        int fontSize = layer.getFontSize();
        
        drawText(graphics2D, color, text, x, y, fontName, fontStyle, fontSize);
    }
    
    void drawText(Graphics2D graphics2D, Color color, String text, int x, int y, 
                        String fontName, int fontStyle, int fontSize) {
        Font font = new Font(fontName, fontStyle, fontSize);
        graphics2D.setFont(font);
        graphics2D.setColor(color);
        
        graphics2D.drawString(text, x, y);
    }
}
