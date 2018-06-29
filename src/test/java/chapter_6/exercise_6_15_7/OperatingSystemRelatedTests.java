package chapter_6.exercise_6_15_7;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperatingSystemRelatedTests {

    @Rule
    public PreserveOperatingSystemPropertiesRule preserveOperatingSystemPropertiesRule =
            new PreserveOperatingSystemPropertiesRule();

    @Test
    public void shouldSetOSNameSystemProperty() {
        System.setProperty("os.name", "Pear");
        System.setProperty("os.version", "100.0");
        System.setProperty("os.arch", "x256");
        System.setProperty("line separator", "|");

        assertEquals("Pear", System.getProperty("os.name"));
        assertEquals("100.0", System.getProperty("os.version"));
        assertEquals("x256", System.getProperty("os.arch"));
        assertEquals("|", System.getProperty("line separator"));
    }
}
