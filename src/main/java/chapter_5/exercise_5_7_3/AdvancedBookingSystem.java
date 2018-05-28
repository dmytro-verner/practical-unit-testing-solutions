package chapter_5.exercise_5_7_3;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class AdvancedBookingSystem {

    private Set<Classroom> classrooms;

    public AdvancedBookingSystem() {
        classrooms = Collections.emptySet();
    }

    public AdvancedBookingSystem(Set<Classroom> classrooms) {
        if(classrooms == null)
            throw new IllegalArgumentException("Classroom list shouldn't be null.");
        if(classrooms.contains(null))
            throw new IllegalArgumentException("Classrooms shouldn't be null.");
        this.classrooms = classrooms;
    }

    public Set<Classroom> getExistingClassrooms(){
        return new HashSet<>(classrooms);
    }

    public List<Classroom> getAllAvailableClassrooms(DayOfWeekHour dayOfWeekHour) {
        return classrooms.stream()
                .filter(c -> c.getBookedDateTimeList()
                                .stream()
                                .noneMatch(b -> b.getDayOfWeek().equals(
                                        dayOfWeekHour.getDayOfWeek()) && b.getHour() == dayOfWeekHour.getHour()))
                .collect(Collectors.toList());
    }

    public void book(String classroomId) {
        Classroom classroom = classrooms.stream()
                .filter(c -> c.getClassroomId().equals(classroomId))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);

        if(classroom.isDayOfWeekTimeAvailable(DayOfWeekHour.now()))
            classroom.setBookDateTime(DayOfWeekHour.of(LocalDateTime.now().getDayOfWeek(), LocalTime.now().getHour()));
        else
            throw new IllegalArgumentException("Can't double book at the same day of week: " +
                    DayOfWeekHour.now().getDayOfWeek() + " and hour: " + DayOfWeekHour.now().getHour());
    }
}
