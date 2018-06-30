package chapter_6.exercise_6_15_8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,
        ElementType.TYPE})
public @interface Retry {

    /**
     * @return the number of time to try this method before the failure is propagated through
     */
    int times() default 2;
}
