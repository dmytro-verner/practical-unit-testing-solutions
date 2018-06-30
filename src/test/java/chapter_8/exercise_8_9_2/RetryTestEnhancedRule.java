package chapter_8.exercise_8_9_2;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RetryTestEnhancedRule implements TestRule {

    private Throwable[] errors = new Throwable[0];

    private int currentAttempt;

    @Override
    public Statement apply(Statement base, Description description) {
        Retry retryAnnotation = description.getAnnotation(Retry.class);
        if(retryAnnotation == null) {
            return base;
        }
        int times = retryAnnotation.times();
        if(times < 1){
            throw new IllegalArgumentException("@" + Retry.class.getSimpleName() +
                    " can't be used with 'times' less than 1");
        }
        errors = new Throwable[times];

        return new Statement() {
            @Override
            public void evaluate() {
                while (currentAttempt < times) {
                    try {
                        base.evaluate();
                        return;
                    } catch (Throwable throwable) {
                        errors[currentAttempt] = throwable;
                        currentAttempt++;
                    }
                }
                throw RetryException.from(errors);
            }
        };
    }
}
