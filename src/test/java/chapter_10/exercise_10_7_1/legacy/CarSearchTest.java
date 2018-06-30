package chapter_10.exercise_10_7_1.legacy;

import chapter_10.exercise_10_7_1.*;
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

        Engine engine1 = mock(Engine.class);
        Engine engine2 = mock(Engine.class);
        when(engine1.getNumberOfCylinders()).thenReturn(7);
        when(engine2.getNumberOfCylinders()).thenReturn(4);

        when(car1.getEngine()).thenReturn(engine1);
        when(car2.getEngine()).thenReturn(engine2);
        when(car3.getEngine()).thenReturn(engine1);

        when(car1.getColor()).thenReturn(Color.BLACK);
        when(car2.getColor()).thenReturn(Color.RED);
        when(car3.getColor()).thenReturn(Color.RED);

        Manufacturer manufacturer1 = mock(Manufacturer.class);
        Manufacturer manufacturer2 = mock(Manufacturer.class);

        when(manufacturer1.getName()).thenReturn("Ferrari");
        when(manufacturer2.getName()).thenReturn("Audi");

        when(car1.getManufacturer()).thenReturn(manufacturer1);
        when(car2.getManufacturer()).thenReturn(manufacturer2);
        when(car3.getManufacturer()).thenReturn(manufacturer1);

        carSearch.addCar(car1);
        carSearch.addCar(car2);
        carSearch.addCar(car3);

        List<Car> sportCars = carSearch.findSportCars();
        assertEquals(1, sportCars.size());
        assertEquals(car3, sportCars.get(0));
    }
}
