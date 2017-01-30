package denv.graphics.textoverimage.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * TextOverImageConfiguration.
 * 
 */
public class TextOverImageConfiguration {
    
    private int width;
    private int height;
    private ColorRGBA color = new ColorRGBA();
    private List<ImageLayer> imageLayers = new ArrayList<>();

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ColorRGBA getColor() {
        return color;
    }

    public void setColor(ColorRGBA color) {
        this.color = color;
    }

    public List<ImageLayer> getImageLayers() {
        return imageLayers;
    }
    
    public boolean addImageLayer(ImageLayer imageLayer) {
        return imageLayers.add(imageLayer);
    }

    public void setImageLayers(List<ImageLayer> imageLayers) {
        this.imageLayers = imageLayers;
    }
    
    public static class TextOverImageConfigurationBuilder {
        private TextOverImageConfiguration configuration = new TextOverImageConfiguration();
        
        public TextOverImageConfiguration build() {
            return configuration;
        }

        public TextOverImageConfigurationBuilder() {}
        
        public TextOverImageConfigurationBuilder(int width, int height) {
            configuration.setWidth(width);
            configuration.setHeight(height);
        }

        public TextOverImageConfigurationBuilder(int width, int height, ColorRGBA backgroundColor) {
            configuration.setWidth(width);
            configuration.setHeight(height);
            configuration.setColor(backgroundColor);
        }
        
        public TextOverImageConfigurationBuilder withImageLayer(ImageLayer imageLayer) {
            configuration.addImageLayer(imageLayer);
            return this;
        }
        
        public TextOverImageConfigurationBuilder withWidth(int width) {
            configuration.setWidth(width);
            return this;
        }

        public TextOverImageConfigurationBuilder withHeight(int height) {
            configuration.setWidth(height);
            return this;
        }
        
        public TextOverImageConfigurationBuilder withBackgroundColorRGBA(ColorRGBA colorRGBA) {
            configuration.setColor(colorRGBA);
            return this;
        }
    }
}
