/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package dat250exp1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test void convertUnitTest() {
        final double precision = 0.01;
        assertTrue( Math.abs(App.convertUnit("mi", "ft", 1) - 5280.0) < precision);
        assertTrue( Math.abs(App.convertUnit("ft", "in", 1) - 12.0) < precision);
        assertTrue( Math.abs(App.convertUnit("m", "m", 1) - 1.0) < precision);
    }

}
