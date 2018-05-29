package chapter_5.exercise_5_7_3;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class AdvancedBookingSystemTests {

    @Test
    public void returnNoExistingClassroomsWhenClassroomListEmpty(){
        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem();

        Assert.assertTrue(bookingSystem.getExistingClassrooms().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIAEWhenClassroomListIsNull(){
        new AdvancedBookingSystem(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIAEWhenThereIsNullProvidedAsClassroom(){
        Classroom classroom1 = mock(Classroom.class);
        new AdvancedBookingSystem(Stream.of(classroom1, null).collect(Collectors.toSet()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIAEWhenThereIsNullProvidedAsClassroomList(){
        new AdvancedBookingSystem(null);
    }

    @Test
    public void returnExistingClassroomWhenThereIsClassroomInList(){
        Classroom classroom1 = mock(Classroom.class);
        when(classroom1.getClassroomId()).thenReturn("C1");
        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Stream.of(classroom1).collect(Collectors.toSet()));

        Assert.assertEquals(1, bookingSystem.getExistingClassrooms().size());
        Assert.assertEquals(1, bookingSystem.getExistingClassrooms().stream().filter(c -> c.getClassroomId().equals("C1")).count());
    }

    @Test
    public void returnExistingClassroomsWhenThereAreClassroomsInList(){
        Classroom classroom1 = mock(Classroom.class);
        Classroom classroom2 = mock(Classroom.class);
        when(classroom1.getClassroomId()).thenReturn("C1");
        when(classroom2.getClassroomId()).thenReturn("C2");
        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Stream.of(classroom1, classroom2).collect(Collectors.toSet()));

        Assert.assertEquals(2, bookingSystem.getExistingClassrooms().size());
        Assert.assertEquals(1, bookingSystem.getExistingClassrooms().stream().filter(c -> c.getClassroomId().equals("C2")).count());
        Assert.assertEquals(1, bookingSystem.getExistingClassrooms().stream().filter(c -> c.getClassroomId().equals("C1")).count());
    }

    @Test
    public void listsAvailableNowClassroomWhenOneInListAndAvailable(){
        Classroom classroom1 = mock(Classroom.class);
        when(classroom1.getClassroomId()).thenReturn("C1");

        AdvancedBookingSystem advancedBookingSystem = new AdvancedBookingSystem(Stream.of(classroom1).collect(Collectors.toSet()));
        List<Classroom> allAvailableClassrooms = advancedBookingSystem.getAllAvailableClassrooms(DayOfWeekHour.now());

        Assert.assertEquals(1, allAvailableClassrooms.size());
        Assert.assertEquals("C1", allAvailableClassrooms.get(0).getClassroomId());
    }

    @Test
    public void listsNoAvailableNowClassroomsWhenOneInListAndBooked(){
        Classroom classroom1 = mock(Classroom.class);
        when(classroom1.getClassroomId()).thenReturn("C1");
        when(classroom1.getBookedDateTimeList()).thenReturn(Collections.singletonList(DayOfWeekHour.now()));

        AdvancedBookingSystem advancedBookingSystem = new AdvancedBookingSystem(Stream.of(classroom1).collect(Collectors.toSet()));
        List<Classroom> allAvailableClassrooms = advancedBookingSystem.getAllAvailableClassrooms(DayOfWeekHour.now());

        Assert.assertEquals(0, allAvailableClassrooms.size());
    }

    private static Object[] dateTimes(){
        return new Object[]{
                new Object[]{DayOfWeekHour.now().minusDaysOfWeek(2)},
                new Object[] {DayOfWeekHour.of(LocalDate.now().getDayOfWeek(), LocalTime.now().minusHours(1).getHour())},
                new Object[] {DayOfWeekHour.of(LocalDate.now().getDayOfWeek(), LocalTime.now().plusHours(1).getHour())},
                new Object[] {DayOfWeekHour.now().plusDaysOfWeek(3)}
        };
    }

    @Test
    @Parameters(method = "dateTimes")
    public void listsAvailableInPastAndFutureClassroomsWhenThereAreBookedAndAvailableOnes(DayOfWeekHour dayOfWeekHour){
        Classroom classroom1 = mock(Classroom.class);
        Classroom classroom2 = mock(Classroom.class);
        Classroom classroom3 = mock(Classroom.class);

        when(classroom1.getBookedDateTimeList()).thenReturn(Collections.singletonList(dayOfWeekHour));

        AdvancedBookingSystem advancedBookingSystem = new AdvancedBookingSystem(
                Stream.of(classroom1, classroom2, classroom3).collect(Collectors.toSet()));
        List<Classroom> allAvailableClassrooms = advancedBookingSystem.getAllAvailableClassrooms(dayOfWeekHour);

        Assert.assertEquals(2, allAvailableClassrooms.size());
        Assert.assertEquals(Stream.of(classroom2, classroom3).collect(Collectors.toSet()), new HashSet<>(allAvailableClassrooms));
    }

    @Test
    public void bookNowByNameWhenThereIsOneAvailableClassroom(){
        Classroom classroom1 = mock(Classroom.class);
        when(classroom1.getClassroomId()).thenReturn("C1");
        when(classroom1.isDayOfWeekTimeAvailable(DayOfWeekHour.now())).thenReturn(true);
        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Stream.of(classroom1).collect(Collectors.toSet()));

        bookingSystem.book("C1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIAEOnDoubleBooking(){
        Classroom classroom1 = mock(Classroom.class);
        when(classroom1.getClassroomId()).thenReturn("C1");
        when(classroom1.isDayOfWeekTimeAvailable(DayOfWeekHour.now())).thenReturn(true);
        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Stream.of(classroom1).collect(Collectors.toSet()));

        bookingSystem.book("C1");
        when(classroom1.isDayOfWeekTimeAvailable(DayOfWeekHour.now())).thenReturn(false);

        bookingSystem.book("C1");
    }

    @Test
    public void canBookMultipleClassroomsAtTheSameTime(){
        Classroom classroom1 = mock(Classroom.class);
        Classroom classroom2 = mock(Classroom.class);
        when(classroom1.getClassroomId()).thenReturn("C1");
        when(classroom2.getClassroomId()).thenReturn("C2");
        when(classroom2.isDayOfWeekTimeAvailable(DayOfWeekHour.now())).thenReturn(true);
        when(classroom1.isDayOfWeekTimeAvailable(DayOfWeekHour.now())).thenReturn(true);
        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Stream.of(classroom1, classroom2)
                .collect(Collectors.toSet()));

        bookingSystem.book("C1");
        bookingSystem.book("C2");
    }

    @Test
    public void returnsAllAvailableClassroomsWithProjector(){
        Classroom classroom1 = mock(Classroom.class);
        Classroom classroom2 = mock(Classroom.class);
        Classroom classroom3 = mock(Classroom.class);

        when(classroom1.getEquipmentAvailability()).thenReturn(new HashMap<Equipment, Boolean>(){{
                put(Equipment.PROJECTOR, false);
                put(Equipment.WI_FI, true);
            }
        });
        when(classroom1.getCapacity()).thenReturn(25);
        when(classroom1.getClassroomId()).thenReturn("C1");

        when(classroom2.getEquipmentAvailability()).thenReturn(new HashMap<Equipment, Boolean>(){{
                put(Equipment.PROJECTOR, true);
                put(Equipment.WI_FI, false);
            }
        });
        when(classroom2.getCapacity()).thenReturn(25);
        when(classroom2.getClassroomId()).thenReturn("C2");

        when(classroom3.getEquipmentAvailability()).thenReturn(new HashMap<Equipment, Boolean>(){{
                put(Equipment.PROJECTOR, true);
                put(Equipment.WI_FI, false);
            }
        });
        when(classroom3.getCapacity()).thenReturn(19);
        when(classroom3.getClassroomId()).thenReturn("C3");

        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Stream.of(classroom1, classroom2)
                .collect(Collectors.toSet()));

        List<Classroom> allAvailableClassrooms = bookingSystem.getAllAvailableClassrooms(20, Collections.singletonList(Equipment.PROJECTOR));

        Assert.assertEquals(1, allAvailableClassrooms.size());
        Assert.assertEquals("C2", allAvailableClassrooms.get(0).getClassroomId());
    }

    @Test
    public void returnsAllAvailableClassroomsWithProjectorAndWiFi(){
        Classroom classroom1 = mock(Classroom.class);
        Classroom classroom2 = mock(Classroom.class);

        when(classroom1.getEquipmentAvailability()).thenReturn(new HashMap<Equipment, Boolean>(){{
                put(Equipment.PROJECTOR, false);
                put(Equipment.WI_FI, true);
            }
        });
        when(classroom1.getCapacity()).thenReturn(25);
        when(classroom1.getClassroomId()).thenReturn("C1");

        when(classroom2.getEquipmentAvailability()).thenReturn(new HashMap<Equipment, Boolean>(){{
                put(Equipment.PROJECTOR, true);
                put(Equipment.WI_FI, true);
            }
        });
        when(classroom2.getCapacity()).thenReturn(25);
        when(classroom2.getClassroomId()).thenReturn("C2");

        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Stream.of(classroom1, classroom2)
                .collect(Collectors.toSet()));

        List<Classroom> allAvailableClassrooms = bookingSystem.getAllAvailableClassrooms(20, Arrays.asList(Equipment.PROJECTOR, Equipment.WI_FI));

        Assert.assertEquals(1, allAvailableClassrooms.size());
        Assert.assertEquals("C2", allAvailableClassrooms.get(0).getClassroomId());
    }

    @Test
    public void returnsAllAvailableClassroomsWithNoRequirementsForEquipment(){
        Classroom classroom1 = mock(Classroom.class);
        Classroom classroom2 = mock(Classroom.class);

        when(classroom1.getEquipmentAvailability()).thenReturn(new HashMap<Equipment, Boolean>(){{
                put(Equipment.PROJECTOR, false);
                put(Equipment.WI_FI, true);
            }
        });
        when(classroom1.getCapacity()).thenReturn(25);
        when(classroom1.getClassroomId()).thenReturn("C1");

        when(classroom2.getEquipmentAvailability()).thenReturn(new HashMap<Equipment, Boolean>(){{
                put(Equipment.PROJECTOR, true);
                put(Equipment.WI_FI, false);
            }
        });
        when(classroom2.getCapacity()).thenReturn(25);
        when(classroom2.getClassroomId()).thenReturn("C2");

        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Stream.of(classroom1, classroom2)
                .collect(Collectors.toSet()));

        List<Classroom> allAvailableClassrooms = bookingSystem.getAllAvailableClassrooms(20, Collections.emptyList());

        Assert.assertEquals(2, allAvailableClassrooms.size());
    }

    @Test
    public void returnsAllAvailableClassroomsWithCapacityEqualOrAbove(){
        Classroom classroom1 = mock(Classroom.class);
        Classroom classroom2 = mock(Classroom.class);

        when(classroom1.getCapacity()).thenReturn(19);
        when(classroom1.getClassroomId()).thenReturn("C1");

        when(classroom2.getCapacity()).thenReturn(20);
        when(classroom2.getClassroomId()).thenReturn("C2");

        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Stream.of(classroom1, classroom2)
                .collect(Collectors.toSet()));

        List<Classroom> allAvailableClassrooms = bookingSystem.getAllAvailableClassrooms(20, Collections.emptyList());

        Assert.assertEquals(1, allAvailableClassrooms.size());
        Assert.assertEquals("C2", allAvailableClassrooms.get(0).getClassroomId());
    }

    private static Object[] classroomsCapacityLessThanOne(){
        return new Object[] {
                new Object[] {0},
                new Object[] {-1}
        };
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "classroomsCapacityLessThanOne")
    public void throwsIAEOnGettingAvailableClassroomsWithCapacityLowerThanOne(int capacity){
        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Collections.emptySet());

        bookingSystem.getAllAvailableClassrooms(capacity, Collections.emptyList());
    }

    @Test
    public void returnsAllAvailableClassroomsWithProjectorAndCapacityEqualOrAbove(){
        Classroom classroom1 = mock(Classroom.class);
        Classroom classroom2 = mock(Classroom.class);
        Classroom classroom3 = mock(Classroom.class);

        when(classroom1.getEquipmentAvailability()).thenReturn(new HashMap<Equipment, Boolean>(){{
                put(Equipment.PROJECTOR, true);
                put(Equipment.WI_FI, true);
            }
        });
        when(classroom1.getCapacity()).thenReturn(19);
        when(classroom1.getClassroomId()).thenReturn("C1");

        when(classroom2.getEquipmentAvailability()).thenReturn(new HashMap<Equipment, Boolean>(){{
                put(Equipment.PROJECTOR, true);
                put(Equipment.WI_FI, false);
            }
        });
        when(classroom2.getCapacity()).thenReturn(20);
        when(classroom2.getClassroomId()).thenReturn("C2");

        when(classroom3.getEquipmentAvailability()).thenReturn(new HashMap<Equipment, Boolean>(){{
            put(Equipment.PROJECTOR, false);
            put(Equipment.WI_FI, false);
        }
        });
        when(classroom3.getCapacity()).thenReturn(20);
        when(classroom3.getClassroomId()).thenReturn("C3");

        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Stream.of(classroom1, classroom2)
                .collect(Collectors.toSet()));

        List<Classroom> allAvailableClassrooms = bookingSystem.getAllAvailableClassrooms(20, Collections.singletonList(Equipment.PROJECTOR));

        Assert.assertEquals(1, allAvailableClassrooms.size());
        Assert.assertEquals("C2", allAvailableClassrooms.get(0).getClassroomId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIAEOnNullEquipmentList(){
        Classroom classroom1 = mock(Classroom.class);
        Classroom classroom2 = mock(Classroom.class);

        AdvancedBookingSystem bookingSystem = new AdvancedBookingSystem(Stream.of(classroom1, classroom2)
                .collect(Collectors.toSet()));

        bookingSystem.getAllAvailableClassrooms(20, null);
    }
}
