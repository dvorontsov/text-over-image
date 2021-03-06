package denv.graphics.textoverimage.dto;

/**
 * Gradient.
 *
 */
public class Gradient {

    private ColorRGBA source;
    private ColorRGBA dest;

    public Gradient() {}

    public Gradient(ColorRGBA source, ColorRGBA dest) {
        this.source = source;
        this.dest = dest;
    }

    public ColorRGBA getDest() {
        return dest;
    }

    public void setDest(ColorRGBA dest) {
        this.dest = dest;
    }

    public ColorRGBA getSource() {
        return source;
    }

    public void setSource(ColorRGBA source) {
        this.source = source;
    }
}
