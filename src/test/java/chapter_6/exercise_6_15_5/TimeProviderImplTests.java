package chapter_6.exercise_6_15_5;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class TimeProviderImplTests {

    private Calendar calendar = mock(Calendar.class);
    private TimeProvider timeProvider = new TimeProviderImpl(calendar);

    private static Object[] morningHours() {
        return new Object[] { new Object[] {0}, new Object[] {11}};
    }

    @Test
    @Parameters(method = "morningHours")
    public void shouldReturnIsMorningTrueForMorningHours(int hour) {
        when(calendar.get(Calendar.HOUR_OF_DAY)).thenReturn(hour);

        assertTrue("Should return true on isMorning request with " + hour + " hour.", timeProvider.isMorning());
    }

    private static Object[] afternoonHours() {
        return new Object[] { new Object[] {12}, new Object[] {23}};
    }

    @Test
    @Parameters(method = "afternoonHours")
    public void shouldReturnIsMorningFalseForAfternoonHours(int hour) {
        when(calendar.get(Calendar.HOUR_OF_DAY)).thenReturn(hour);

        assertFalse("Should return false on isMorning request with " + hour + " hour.", timeProvider.isMorning());
    }
}
