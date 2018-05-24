package chapter_4.exercise_4_10_3;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingSystem {

    private List<LocalTime> bookedHours;

    BookingSystem(){
        bookedHours = new ArrayList<>();
    }

    public List<LocalTime> getBookedHours() {
        return bookedHours;
    }

    /**
     * Allows to book only whole clock hours
     * @param startHour hour to be starting point of booking
     * @param numberOfHours number of hours to have a booking
     * @throws IllegalArgumentException if startHour is not whole clock hour or there is a try to book a booked hour
     */
    public void addBooking(LocalTime startHour, int numberOfHours) {
        if(startHour.getMinute() != 0)
            throw new IllegalArgumentException("Non regular whole clock hours are not allowed");

        if(bookedHours.contains(startHour))
            throw new IllegalArgumentException("Hours shouldn't be double booked");

        bookedHours.add(startHour);

        int hourNumberToAdd = 1;
        while(numberOfHours > 1){
            numberOfHours--;

            LocalTime timeToAddToBooking = startHour.plusHours(hourNumberToAdd);
            if(bookedHours.contains(timeToAddToBooking))
                throw new IllegalArgumentException("Hours shouldn't be double booked");
            bookedHours.add(timeToAddToBooking);
            hourNumberToAdd++;
        }
    }
}
