package chapter_4.exercise_4_10_3;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class BookingSystemTest {

    private BookingSystem bookingSystem = new BookingSystem();

    @Test
    public void shouldReturnNoBookedHoursWhenNoBooking(){
        Assert.assertTrue(bookingSystem.getBookedHours().isEmpty());
    }

    private static Object[] validBookedHours(){
        return new Object[] {
                new Object[] {
                        Arrays.asList(
                                LocalTime.of(12, 0)),
                        LocalTime.of(12, 0), 1},
                new Object[] {
                        Arrays.asList(
                                LocalTime.of(12, 0),
                                LocalTime.of(13, 0)),
                        LocalTime.of(12, 0), 2},
                new Object[] {
                        Arrays.asList(
                                LocalTime.of(18, 0),
                                LocalTime.of(19, 0),
                                LocalTime.of(20, 0),
                                LocalTime.of(21, 0),
                                LocalTime.of(22, 0),
                                LocalTime.of(23, 0)),
                        LocalTime.of(18, 0), 6},
        };
    }

    @Test
    @Parameters(method = "validBookedHours")
    public void shouldReturnBookedHours(List<LocalTime> expectedBookedHours, LocalTime startBookingHour, int numberOfHours){
        bookingSystem.addBooking(startBookingHour, numberOfHours);
        Assert.assertEquals("Starting booked hour " + startBookingHour.toString() + " plus next "
                        + numberOfHours + " hours should be booked",
                expectedBookedHours,
                bookingSystem.getBookedHours());
    }

    private static Object[] doubleBookedHours(){
        return new Object[]{
                new Object[] {
                        LocalTime.of(1, 0), 1,
                        LocalTime.of(1, 0), 1
                },
                new Object[] {
                        LocalTime.of(4, 0), 2,
                        LocalTime.of(5, 0), 2
                },
                new Object[] {
                        LocalTime.of(5, 0), 2,
                        LocalTime.of(4, 0), 2
                }
        };
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "doubleBookedHours")
    public void throwIAEOnDoubleBooking(LocalTime firstStartBookingTime, int firstNumberOfHoursToBook,
                                            LocalTime secondStartBookingTime, int secondNumberOfHoursToBook ){
        bookingSystem.addBooking(firstStartBookingTime, firstNumberOfHoursToBook);
        bookingSystem.addBooking(secondStartBookingTime, secondNumberOfHoursToBook);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIAEOnNonRegularWholeClockHours(){
        bookingSystem.addBooking(LocalTime.of(2, 30), 2);
    }
}
