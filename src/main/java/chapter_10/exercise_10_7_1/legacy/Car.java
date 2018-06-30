package chapter_10.exercise_10_7_1.legacy;

import chapter_10.exercise_10_7_1.Color;
import chapter_10.exercise_10_7_1.Engine;
import chapter_10.exercise_10_7_1.Manufacturer;

public interface Car {

    Engine getEngine();
    Color getColor();
    Manufacturer getManufacturer();
}
