package chapter_10.exercise_10_7_1.refactored;

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
            if (car.isSportCar()) {
                sportCars.add(car);
            }
        }
        return sportCars;
    }
}
