package chapter_3.exercise_3_11_2;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class StringUtilTest {

    @Test(expected = IllegalArgumentException.class)
    public void reverseOnNullThrowsIAE() {
        StringUtil.reverse(null);
    }

    @Test
    public void reversesEmptyStringToEmptyString(){
        assertEquals("", StringUtil.reverse(""));
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
        assertEquals(expectedAfterReverse, StringUtil.reverse(strToRevert));
    }

    @Test
    public void reversesCaseSensitive(){
        String inputString = "AbCd";
        String expectedString = "dCbA";

        assertEquals(expectedString, StringUtil.reverse(inputString));
    }
}