package chapter_10.exercise_10_7_1.legacy;

import chapter_10.exercise_10_7_1.Color;

import java.util.ArrayList;
import java.util.List;

public class CarSearch {

    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> findSportCars() {
        List<Car> sportCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getEngine().getNumberOfCylinders() > 6
                    && Color.RED == car.getColor()
                    && "Ferrari".equals(car.getManufacturer().getName())) {
                sportCars.add(car);
            }
        }
        return sportCars;
    }
}
