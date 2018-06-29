package chapter_6.exercise_6_15_5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelloRedesignedTests {

    TimeProvider timeProvider = mock(TimeProvider.class);
    HelloRedesigned helloRedesigned = new HelloRedesigned(timeProvider);

    @Test
    public void shouldReturnGoodMorningForMorningHours() {
        when(timeProvider.isMorning()).thenReturn(true);

        assertEquals("Should return " + DayTimeTypeMessage.MORNING + " for morning hour.",
                DayTimeTypeMessage.MORNING, helloRedesigned.sayHello());
    }

    @Test
    public void shouldReturnGoodAfternoonForAfternoonHours() {
        when(timeProvider.isMorning()).thenReturn(false);

        assertEquals("Should return " + DayTimeTypeMessage.AFTERNOON + " for afternoon hour.",
                DayTimeTypeMessage.AFTERNOON, helloRedesigned.sayHello());
    }
}
