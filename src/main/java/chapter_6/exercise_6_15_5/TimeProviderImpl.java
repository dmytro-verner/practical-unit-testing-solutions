package chapter_6.exercise_6_15_5;

import java.util.Calendar;

public class TimeProviderImpl implements TimeProvider {

    private Calendar calendar;

    public TimeProviderImpl(Calendar calendar) {
        this.calendar = calendar;
    }

    public boolean isMorning() {
        return calendar.get(Calendar.HOUR_OF_DAY) < 12;
    }
}
