package chapter_8.exercise_8_9_3;

import java.util.Map;
import java.util.TreeMap;

public final class ExecutionResultsHolder {

    private static Map<String, Long> executionResults = new TreeMap<>();

    public static Map<String, Long> getExecutionResults() {
        return executionResults;
    }

    public static void put(String key, Long executionTime) {
        executionResults.put(key, executionTime);
    }

    public static Map<String, Long > reverseSortByValue() {
        return MapUtil.reverseSortByValue(executionResults);
    }
}
