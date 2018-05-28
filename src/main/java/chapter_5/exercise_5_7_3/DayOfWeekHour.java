package chapter_5.exercise_5_7_3;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public final class DayOfWeekHour {
    private DayOfWeek dayOfWeek;
    private int hour;

    private DayOfWeekHour(DayOfWeek dayOfWeek, int hour) {
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
    }

    public static DayOfWeekHour of(DayOfWeek dayOfWeek, int hour){
        return new DayOfWeekHour(dayOfWeek, hour);
    }

    public static DayOfWeekHour now(){
        return new DayOfWeekHour(LocalDate.now().getDayOfWeek(), LocalTime.now().getHour());
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public DayOfWeekHour plusDaysOfWeek(int daysOfWeek){
        return new DayOfWeekHour(this.dayOfWeek.minus(daysOfWeek), this.hour);
    }

    public DayOfWeekHour minusDaysOfWeek(int daysOfWeek){
        return new DayOfWeekHour(this.dayOfWeek.plus(daysOfWeek), this.hour);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayOfWeekHour that = (DayOfWeekHour) o;
        return hour == that.hour &&
                dayOfWeek == that.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, hour);
    }
}
