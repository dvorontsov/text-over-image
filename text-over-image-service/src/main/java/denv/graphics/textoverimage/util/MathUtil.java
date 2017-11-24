package denv.graphics.textoverimage.util;

/**
 * Created by Den on 11/16/2017.
 *
 */
public class MathUtil {

    /**
     * Linear interpolation.  Blends between between two values.
     *
     * @param startValue
     * @param endValue
     * @param t - parameter, where 0 =< t =< 1
     * @return weighted integer result between two values.
     */
    public static int lerp(int startValue, int endValue, float t) {
        if(t < 0) {
            return startValue;
        } else if(t > 1) {
            return endValue;
        } else {
            return (int)((1 - t) * startValue + t * endValue);
        }
    }
}
