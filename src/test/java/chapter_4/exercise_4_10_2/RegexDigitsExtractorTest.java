package chapter_4.exercise_4_10_2;

import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Test;
import junitparams.Parameters;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class RegexDigitsExtractorTest {

    private RegexDigitsExtractor regexDigitsExtractor = new RegexDigitsExtractor();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIAEOnNull(){
        regexDigitsExtractor.extractThreeOrMoreConsecutiveNumbers(null);
    }

    @Test
    public void shouldReturnEmptyListOnEmptyString(){
        Assert.assertTrue("Empty list of integers for an empty string",
                regexDigitsExtractor.extractThreeOrMoreConsecutiveNumbers("").isEmpty());
    }

    private static Object[] stringWithLessThan3ConsecutiveDigits(){
        return new Object[] {
                new Object[] {"abc 12"},
                new Object[] {"12 abc"},
                new Object[] {"12 abc 12"},
                new Object[] {"1 2 3"},
                new Object[] {"abc"},
                new Object[] {"12 34"},
        };
    }

    @Test
    @Parameters(method = "stringWithLessThan3ConsecutiveDigits")
    public void shouldReturnEmptyListOnStringWithLessThan3ConsecutiveDigits(String stringWithLessThan3ConsecutiveDigits){
        Assert.assertTrue("Empty list of integers on a string with less than 3 consecutive digits",
                regexDigitsExtractor.extractThreeOrMoreConsecutiveNumbers(stringWithLessThan3ConsecutiveDigits).isEmpty());
    }

    @Test
    public void shouldReturnListOfIntegersFromStringWith3AndMoreConsecutiveIntegers(){
        List<String> expectedList = Arrays.asList("1234", "345", "678");

        Assert.assertEquals(expectedList,
                regexDigitsExtractor.extractThreeOrMoreConsecutiveNumbers("1234 cdefg 345 12bbb33 678tt3_4_4"));
    }
}
