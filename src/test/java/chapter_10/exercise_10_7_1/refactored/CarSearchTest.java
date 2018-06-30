package chapter_10.exercise_10_7_1.refactored;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarSearchTest {

    @Test
    public void shouldAddOnlySportCars() {
        CarSearch carSearch = new CarSearch();

        Car car1 = mock(Car.class);
        Car car2 = mock(Car.class);
        Car car3 = mock(Car.class);

        when(car1.isSportCar()).thenReturn(false);
        when(car2.isSportCar()).thenReturn(true);
        when(car3.isSportCar()).thenReturn(false);

        carSearch.addCar(car1);
        carSearch.addCar(car2);
        carSearch.addCar(car3);

        List<Car> sportCars = carSearch.findSportCars();
        assertEquals(1, sportCars.size());
        assertEquals(car2, sportCars.get(0));
    }
}
