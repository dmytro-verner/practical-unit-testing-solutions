package chapter_6.exercise_6_15_4;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class HelpDeskTests {

    private HelpDesk helpDesk;
    private TimeProvider timeProvider;
    private Issue issue;

    @Before
    public void setUp() {
        timeProvider = mock(TimeProvider.class);
        helpDesk = new HelpDesk(timeProvider);

        issue = mock(Issue.class);
    }

    private static Object[] businessAndNotFridayDayAndHour() {
        return new Object[] {
                new Object[] {2, 1},
                new Object[] {2, 21},
                new Object[] {3, 4},
                new Object[] {3, 21},
                new Object[] {4, 4},
                new Object[] {4, 21},
                new Object[] {5, 1},
                new Object[] {5, 21}
        };
    }

    @Test
    @Parameters(method = "businessAndNotFridayDayAndHour")
    public void shouldHandleWhenItsBusinessDayAndNotFriday(int dayOfWeek, int hour) {
        when(timeProvider.getTime()).thenReturn(getCalendar(dayOfWeek, hour));

        assertTrue("Should handle issue when it's business day.", helpDesk.willHandleIssue(issue));
    }

    private static Object[] fridayDayWhenIssueIsToBeHandled() {
        return new Object[] {
                new Object[] {5, 12},
                new Object[] {5, 17}
        };
    }

    @Test
    @Parameters(method = "fridayDayWhenIssueIsToBeHandled")
    public void shouldHandleWhenItsFridayAndHourIs17OrLess(int dayOfWeek, int hour) {
        when(timeProvider.getTime()).thenReturn(getCalendar(dayOfWeek, hour));

        assertTrue("Should handle issue when it's Friday and hour is 17 or less.", helpDesk.willHandleIssue(issue));
    }

    private static Object[] fridayDayWhenIssueNotToBeHandled() {
        return new Object[] {
                new Object[] {6, 18},
                new Object[] {6, 23}
        };
    }

    @Test
    @Parameters(method = "fridayDayWhenIssueNotToBeHandled")
    public void shouldNotHandleWhenItsFridayAndHourIsAfter17(int dayOfWeek, int hour) {
        when(timeProvider.getTime()).thenReturn(getCalendar(dayOfWeek, hour));

        assertFalse("Shouldn't handle issue when it's Friday and hour is after 17.", helpDesk.willHandleIssue(issue));
    }

    private static Object[] weekendDayAndHour() {
        return new Object[] {
                new Object[] {1, 1},
                new Object[] {1, 23},
                new Object[] {7, 1},
                new Object[] {7, 23}
        };
    }

    @Test
    @Parameters(method = "weekendDayAndHour")
    public void shouldNotHandleWhenItsWeekendDay(int dayOfWeek, int hour) {
        when(timeProvider.getTime()).thenReturn(getCalendar(dayOfWeek, hour));

        assertFalse("Shouldn't handle issue when it's business day.", helpDesk.willHandleIssue(issue));
    }

    private Calendar getCalendar(int dayOfWeek, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        return cal;
    }
}
