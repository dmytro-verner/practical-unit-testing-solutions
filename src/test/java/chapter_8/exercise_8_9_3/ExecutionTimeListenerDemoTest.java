package chapter_8.exercise_8_9_3;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.fail;

public class ExecutionTimeListenerDemoTest {

    @Rule
    public ExecutionTimeListener executionTimeListener = new ExecutionTimeListener();

    @Test
    public void tenMillis() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    public void fiftyMillis() throws InterruptedException {
        Thread.sleep(500);
    }

    @Test
    public void failedAfterTenMillis() throws InterruptedException {
        Thread.sleep(145);
        fail();
    }

    @AfterClass
    public static void printExecutionResults() {
        for (Map.Entry<String, Long> entry : ExecutionResultsHolder.reverseSortByValue().entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
