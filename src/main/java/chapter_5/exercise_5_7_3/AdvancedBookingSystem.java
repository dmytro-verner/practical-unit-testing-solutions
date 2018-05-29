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

    public List<Classroom> getAllAvailableClassrooms(int capacity, List<Equipment> equipmentList) {
        return getAllAvailableClassrooms(DayOfWeekHour.now(), capacity, equipmentList);
    }

    public List<Classroom> getAllAvailableClassrooms(DayOfWeekHour dayOfWeekHour, int minimumCapacity, List<Equipment> equipmentList) {
        if(equipmentList == null)
            throw new IllegalArgumentException("Equipment list shouldn't be null");

        if(minimumCapacity <= 0) {
            throw new IllegalArgumentException("Minimum capacity: " + minimumCapacity + " is less than 1");
        }

        return classrooms.stream()
                .filter(c -> c.getCapacity() >= minimumCapacity &&
                    equipmentList.stream().allMatch(e -> c.getEquipmentAvailability().get(e))
                )
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

        setClassroomBookingStatus(classroom);
    }

    public void book(int minimumCapacity, List<Equipment> equipmentList) {
        if(minimumCapacity <= 0)
            throw new IllegalArgumentException("Minimum capacity " + minimumCapacity + " shouldn't less than 1");

        Classroom bookableClassroom = classrooms.stream()
                .filter(c -> c.getCapacity() >= minimumCapacity &&
                        equipmentList.stream().allMatch(e -> c.getEquipmentAvailability().get(e))
                )
                .filter(c -> c.getBookedDateTimeList()
                        .stream()
                        .noneMatch(b -> b.getDayOfWeek().equals(
                                DayOfWeekHour.now().getDayOfWeek()) && b.getHour() == DayOfWeekHour.now().getHour()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No available classroom with these requirements"));

        setClassroomBookingStatus(bookableClassroom);
    }

    private void setClassroomBookingStatus(Classroom classroom){
        if(classroom.isDayOfWeekTimeAvailable(DayOfWeekHour.now()))
            classroom.setBookDateTime(DayOfWeekHour.of(LocalDateTime.now().getDayOfWeek(), LocalTime.now().getHour()));
        else
            throw new IllegalArgumentException("Can't double book at the same day of week: " +
                    DayOfWeekHour.now().getDayOfWeek() + " and hour: " + DayOfWeekHour.now().getHour());
    }
}
