package chapter_5.exercise_5_7_2;

import java.time.LocalDate;

public interface Message {

    SubscriptionCategory getSubscriptionCategory();

    LocalDate getDate();

    String getMessage();
}
