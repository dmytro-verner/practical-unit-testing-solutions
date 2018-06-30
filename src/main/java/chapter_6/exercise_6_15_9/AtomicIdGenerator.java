package chapter_6.exercise_6_15_9;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicIdGenerator implements IdGenerator {

    private static AtomicLong nextId = new AtomicLong(System.currentTimeMillis());

    public Long nextId() {
        return nextId.incrementAndGet();
    }
}
