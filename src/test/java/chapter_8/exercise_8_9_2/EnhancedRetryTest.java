package chapter_8.exercise_8_9_2;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class EnhancedRetryTest {
    @Rule
    public RetryTestEnhancedRule retryTestRule = new RetryTestEnhancedRule();

    static int firstTestCounter = 0;
    static int secondTestCounter = 0;
    static int thirdTestCounter = 0;

    @Test
    @Retry(times = 3)
    public void shouldFailOnSecondAttempt() {
        firstTestCounter++;
        Assert.fail("failing " + firstTestCounter);
    }

    @Test
    @Retry(times = 4)
    public void shouldFailOnThirdAttempt() {
        secondTestCounter++;
        Assert.fail("failing " + secondTestCounter);
    }

    @Test
    public void shouldNotBeRerun() {
        thirdTestCounter++;
        Assert.fail("failing " + thirdTestCounter);
    }
}