package denv.graphics.textoverimage.dto;

/**
 * ColorRGBA.
 * 
 */
public class ColorRGBA {
    
    private int r;
    private int g;
    private int b;
    private int a;

    public ColorRGBA(int red, int green, int blue, int alpha) {
        this.r = red;
        this.g = green;
        this.b = blue;
        this.a = alpha;
    }

    public ColorRGBA() {}
    
    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
