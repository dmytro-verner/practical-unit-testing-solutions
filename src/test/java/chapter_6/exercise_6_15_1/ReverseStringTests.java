package chapter_6.exercise_6_15_1;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class ReverseStringTests {

    @Test(expected = IllegalArgumentException.class)
    public void reverseOnNullThrowsIAE() {
        StringUtils.reverse(null);
    }

    @Test
    public void reversesEmptyStringToEmptyString(){
        assertEquals("", StringUtils.reverse(""));
    }

    private static Object[] reverseString() {
        return new Object[] {
                new Object[] {"a", "a"},
                new Object[] {"ab", "ba"},
                new Object[] {"aba", "aba"},
                new Object[] {"ds_42<^", "^<24_sd"},
                new Object[] {"a ", " a"},
                new Object[] {" 2", "2 "}
        };
    }

    @Test
    @Parameters(method = "reverseString")
    public void reversesAGivenString(String strToRevert, String expectedAfterReverse) {
        assertEquals(expectedAfterReverse, StringUtils.reverse(strToRevert));
    }

    @Test
    public void reversesCaseSensitive(){
        String inputString = "AbCd";
        String expectedString = "dCbA";

        assertEquals(expectedString, StringUtils.reverse(inputString));
    }
}