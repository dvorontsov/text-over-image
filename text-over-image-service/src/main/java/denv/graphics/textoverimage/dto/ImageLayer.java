package denv.graphics.textoverimage.dto;

/**
 * ImageLayer.
 * 
 */
public class ImageLayer {

    private ImageLayerType type;
    private int xCoordinate;
    private int yCoordinate;
    
    private String text;
    private ColorRGBA color;
    private String fontName;
    private int fontStyle;
    private int fontSize;

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ColorRGBA getColor() {
        return color;
    }

    public void setColor(ColorRGBA color) {
        this.color = color;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public ImageLayerType getType() {
        return type;
    }

    public void setType(ImageLayerType type) {
        this.type = type;
    }

    public static class ImageLayerBuilder {
        private ImageLayer imageLayer = new ImageLayer();
        
        public ImageLayer build() {
            return imageLayer;
        }

        public ImageLayerBuilder(int x, int y) {
            imageLayer.setxCoordinate(x);
            imageLayer.setyCoordinate(y);
        }
        
        public ImageLayerBuilder withText(String text) {
            imageLayer.setText(text);
            return this;
        }
        
        public ImageLayerBuilder withTextColor(ColorRGBA color) {
            imageLayer.setColor(color);
            return this;
        }
        
        public ImageLayerBuilder withFontConfiguration(String fontName, int fontStyle, int fontSize) {
            imageLayer.setFontName(fontName);
            imageLayer.setFontStyle(fontStyle);
            imageLayer.setFontSize(fontSize);
            return this;
        }
    }
}
