package chapter_6.exercise_6_15_6;

import chapter_6.package_6_15_6.OperatingSystem;
import org.junit.Test;

import static chapter_6.exercise_6_15_6.OperatingSystemAssert.assertThat;

public class OperatingSystemTests {

    private OperatingSystem os;

    @Test
    public void testUsingCustomMatcher() {
        os = new OperatingSystem(128, "Pear", "1", 2018);

        assertThat(os).is128bit().hasName("Pear").wasReleasedIn(2018).hasVersion("1");
    }
}
