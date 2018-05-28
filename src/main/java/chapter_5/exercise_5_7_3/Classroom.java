package chapter_5.exercise_5_7_3;

import java.util.List;
import java.util.Map;

public interface Classroom {

    String getClassroomId();

    List<DayOfWeekHour> getBookedDateTimeList();

    void setBookDateTime(DayOfWeekHour dayOfWeekHour);

    boolean isDayOfWeekTimeAvailable(DayOfWeekHour dayOfWeekHour);

    Map<Equipment, Boolean> getEquipmentAvailability();

    int getCapacity();
}
