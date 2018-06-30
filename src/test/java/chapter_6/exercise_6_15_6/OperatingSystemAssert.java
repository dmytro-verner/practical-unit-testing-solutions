package chapter_6.exercise_6_15_6;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

public class OperatingSystemAssert extends GenericAssert<OperatingSystemAssert, OperatingSystem> {

    public OperatingSystemAssert(OperatingSystem actual) {
        super(OperatingSystemAssert.class, actual);
    }

    public static OperatingSystemAssert assertThat(OperatingSystem actual) {
        return new OperatingSystemAssert(actual);
    }

    public OperatingSystemAssert is128bit() {
        isNotNull();
        String errorMessage = "Expected operating system's nbOfBits to be 128 bits, but was " + actual.getNbOfBits();
        Assertions.assertThat(actual.getNbOfBits())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(128);
        return this;
    }

    public OperatingSystemAssert hasName(String name) {
        isNotNull();
        String errorMessage = String.format("Expected operating system's name to be <%s> but was <%s>",
                name, actual.getName());
        Assertions.assertThat(actual.getName())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(name);
        return this;
    }

    public OperatingSystemAssert wasReleasedIn(int releaseYear) {
        isNotNull();
        String errorMessage = String.format("Expected operating system's release year to be <%s> but was <%s>",
                releaseYear, actual.getReleaseYear());
        Assertions.assertThat(actual.getReleaseYear())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(releaseYear);
        return this;
    }

    public OperatingSystemAssert hasVersion(String version) {
        isNotNull();
        String errorMessage = String.format("Expected operating system's version to be <%s> but was <%s>",
                version, actual.getVersion());
        Assertions.assertThat(actual.getVersion())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(version);
        return this;
    }
}
