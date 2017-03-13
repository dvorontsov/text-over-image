package denv.graphics.textoverimage.service;

import com.google.gson.Gson;
import denv.graphics.textoverimage.api.TextOverImageService;
import denv.graphics.textoverimage.dto.ColorRGBA;
import denv.graphics.textoverimage.dto.Gradient;
import denv.graphics.textoverimage.dto.ImageLayer;
import denv.graphics.textoverimage.dto.ImageLayerType;
import denv.graphics.textoverimage.dto.TextOverImageConfiguration;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentSampleModel;
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

    /**
     * Linear interpolation.  Blends between between two values.
     *
     * @param startValue
     * @param endValue
     * @param t - parameter, where 0 =< t =< 1
     * @return
     */
    int lerp(float startValue, float endValue, float t) {
        if(t < 0) {
            return 0;
        } else if(t > 1) {
            return 1;
        } else {
            return (int)((1 - t) * startValue + t * endValue);
        }
    }

    /**
     * Returns true, if configuration contains enough information to render gradient.
     *
     * @param configuration
     * @return
     */
    boolean isGradientBackground(TextOverImageConfiguration configuration) {
        Gradient gradient = configuration.getGradient();
        if(gradient != null && gradient.getSource() != null && gradient.getDest() != null) {
            return true;
        }
        return false;
    }

    boolean isImageBackground(TextOverImageConfiguration configuration) {
        return (configuration.getImageSrc() != null && !configuration.getImageSrc().isEmpty());
    }

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
        float horizontalLineIdx = 0;
        int[] pixels = new int[arraySize];

        if(isImageBackground(configuration)) {
            BufferedImage backgroundImage = null;
            try {
                backgroundImage = ImageIO.read(new File(configuration.getImageSrc()));
                int pixelStride = ((ComponentSampleModel)backgroundImage.getRaster().getSampleModel()).getPixelStride();
                int[] backgroundPixels = new int[width * height * pixelStride];
                backgroundImage.getRaster().getPixels(0, 0, width, height, backgroundPixels);

                int backgroundPixelsIndex = 0;
                if(pixelStride >= 3) {
                    for(int i = 0; i < arraySize; i+=4) {
                        // Red channel
                        pixels[i] = backgroundPixels[backgroundPixelsIndex];
                        // Green channel
                        pixels[i + 1] = backgroundPixels[backgroundPixelsIndex + 1];
                        // Blue channel
                        pixels[i + 2] = backgroundPixels[backgroundPixelsIndex + 2];
                        // Alpha channel
                        pixels[i + 3] = 255;

                        backgroundPixelsIndex +=pixelStride;
                    }
                }
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
            }

        } else if(isGradientBackground(configuration)) {
            for(int i = 0; i < arraySize; i+=4) {
                ColorRGBA source = configuration.getGradient().getSource();
                ColorRGBA dest = configuration.getGradient().getDest();

                if((i % (width * 4)) == 0) {
                    horizontalLineIdx++;
                }

                float linearBlendParameter = horizontalLineIdx / height;

                ColorRGBA result = new ColorRGBA(
                        lerp(source.getR(), dest.getR(), linearBlendParameter),
                        lerp(source.getG(), dest.getG(), linearBlendParameter),
                        lerp(source.getB(), dest.getB(), linearBlendParameter),
                        lerp(source.getA(), dest.getA(), linearBlendParameter));

                // Red channel
                pixels[i] = result.getR();
                // Green channel
                pixels[i + 1] = result.getG();
                // Blue channel
                pixels[i + 2] = result.getB();
                // Alpha channel
                pixels[i + 3] = result.getA();
            }
        } else {
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
        }

        Graphics2D graphics2D = image.createGraphics();
        RenderingHints rn = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, 
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHints(rn);

        List<ImageLayer> textLayers = new ArrayList<>();
        for(ImageLayer layer : configuration.getImageLayers()) {
            if(layer.getType().equals(ImageLayerType.TEXT)) {
                textLayers.add(layer);
            } else {
                applyLayer(pixels, graphics2D, layer, width, height);
            }
        }

        writableRaster.setPixels(0, 0, width, height, pixels);

        for(ImageLayer layer : textLayers) {
            applyLayer(pixels, graphics2D, layer, width, height);
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

    void applyLayer(int[] pixels, Graphics2D graphics2D, ImageLayer layer, int imageWidth, int imageHeight) {
        ImageLayerType layerType = layer.getType();

        switch (layerType) {
            case TEXT: {
                int x = layer.getxCoordinate();
                int y = layer.getyCoordinate();
                Color color = new Color(layer.getColor().getR(), layer.getColor().getG(), layer.getColor().getB(),
                        layer.getColor().getA());
                String text = layer.getText();
                String fontName = layer.getFontName();
                int fontStyle = layer.getFontStyle();
                int fontSize = layer.getFontSize();

                drawText(graphics2D, color, text, x, y, fontName, fontStyle, fontSize);
            } break;
            case COLOR_FILL: {
                int x = layer.getxCoordinate();
                int y = layer.getyCoordinate();

                ColorRGBA colorRGBA = layer.getColor();

                int startIndex = ((y - 1) * imageWidth + x)  * 4;
                int endIndex = pixels.length;

                for(int i = startIndex; i < endIndex; i+=4) {
                    pixels[i] = lerp(pixels[i], colorRGBA.getR(), (float)colorRGBA.getA()/255);
                    pixels[i + 1] = lerp(pixels[i + 1], colorRGBA.getG(), (float)colorRGBA.getA()/255);
                    pixels[i + 2] = lerp(pixels[i + 2], colorRGBA.getB(), (float)colorRGBA.getA()/255);
                    //pixels[i + 3] = lerp(pixels[i + 3], colorRGBA.getA(), (float)colorRGBA.getA()/255);
                }
            } break;

        }
    }
    
    void drawText(Graphics2D graphics2D, Color color, String text, int x, int y, 
                        String fontName, int fontStyle, int fontSize) {
        Font font = new Font(fontName, fontStyle, fontSize);
        graphics2D.setFont(font);
        graphics2D.setColor(color);
        
        graphics2D.drawString(text, x, y);
    }
}
