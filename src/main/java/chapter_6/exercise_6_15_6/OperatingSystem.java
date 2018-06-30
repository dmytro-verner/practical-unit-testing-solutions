package chapter_6.exercise_6_15_6;

public class OperatingSystem {

    private int nbOfBits;

    private String name;

    private String version;

    private int releaseYear;

    public OperatingSystem(int nbOfBits, String name, String version, int releaseYear) {
        this.nbOfBits = nbOfBits;
        this.name = name;
        this.version = version;
        this.releaseYear = releaseYear;
    }

    public int getNbOfBits() {
        return nbOfBits;
    }

    public void setNbOfBits(int nbOfBits) {
        this.nbOfBits = nbOfBits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
