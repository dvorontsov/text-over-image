package denv.graphics.textoverimage.util;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Den on 11/18/2017.
 */
public class MathUtilTest {

    @Test
    public void testLerp() throws Exception {
        int startValue = 0;
        int endValue = 10;
        float parameter = 0.5f;

        int result = MathUtil.lerp(startValue, endValue, parameter);

        assertEquals(result, 5);
    }

    @Test
    public void testLerp_ParamLessThenZero() throws Exception {
        int startValue = 0;
        int endValue = 10;
        float parameter = -0.1f;

        int result = MathUtil.lerp(startValue, endValue, parameter);

        assertEquals(result, startValue);
    }

    @Test
    public void testLerp_ParamGreaterThanOne() throws Exception {
        int startValue = 0;
        int endValue = 10;
        float parameter = 1.1f;

        int result = MathUtil.lerp(startValue, endValue, parameter);

        assertEquals(result, endValue);
    }

}