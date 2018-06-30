package chapter_8.exercise_8_9_2;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * An exception thrown to signal a retry operation has retries more that allowed times
 */
public class RetryException extends RuntimeException {

    public static RetryException from(Throwable[] throwables) {
        StringBuilder message = new StringBuilder("Invoked methods still failed after " + throwables.length + " attempts.");
        for(int i = 0; i < throwables.length; i++) {
            Throwable exception = throwables[1];
            message.append('\n');
            message.append("Attempt ").append(i).append(" threw exception:");
            message.append(stackTraceAsString(exception));
        }
        return new RetryException(message.toString());
    }

    public RetryException(String message) {
        super(message);
    }

    private static String stackTraceAsString(Throwable t) {
        final StringWriter errors = new StringWriter();
        t.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
