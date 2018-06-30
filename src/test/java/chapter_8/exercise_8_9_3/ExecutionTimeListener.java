package chapter_8.exercise_8_9_3;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExecutionTimeListener extends TestWatcher {

    private Map<String, Long> startTimes = new HashMap<>();

    private Map<String, Long> executionResults = new TreeMap<>();

    public Map<String, Long> getExecutionResults() {
        return executionResults;
    }

        @Override
    protected void starting(Description description) {
        startTimes.put(description.getMethodName(), System.currentTimeMillis());
    }

    @Override
    protected void succeeded(Description description) {
        saveResults("ok", description);
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        saveResults("SKIPPED", description);
    }

    @Override
    protected void failed(Throwable e, Description description) {
        saveResults("FAIL", description);
    }

    private void saveResults(String status, Description description) {
        long executionTimeInMillis = System.currentTimeMillis() - startTimes.get(description.getMethodName());

        DateFormat df = new SimpleDateFormat("ss:SSS");
        String formattedTime = df.format(executionTimeInMillis);

        ExecutionResultsHolder.put(String.format("%-7s"
                        + "%-80s" + " "  + " %-10s", status, description.getClassName() +
                        "." + description.getMethodName(), formattedTime),
                executionTimeInMillis);
    }
}
