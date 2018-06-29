package chapter_6.exercise_6_15_7;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class PreserveOperatingSystemPropertiesRule implements TestRule {

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                String osName = System.getProperty("os.name");
                String osVersion = System.getProperty("os.version");
                String osArchitecture = System.getProperty("os.arch");
                String lineSeparator = System.getProperty("line.separator");

                statement.evaluate();

                System.setProperty("os.name", osName);
                System.setProperty("os.version", osVersion);
                System.setProperty("os.arch", osArchitecture);
                System.setProperty("os.version", lineSeparator);
            }
        };
    }
}
