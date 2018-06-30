package chapter_6.exercise_6_15_9;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class AtomicGeneratorParallelTest {

    @Rule
    public ConcurrentRule concurrentRule = new ConcurrentRule();

    @Rule
    public RepeatingRule repeatingRule = new RepeatingRule();

    private IdGenerator idGenerator = new AtomicIdGenerator();
    private Set<Long> ids = new HashSet<>(100);

    @Test
    @Concurrent(count = 10)
    @Repeating(repetition = 150)
    public void idsShouldBeUnique(){
        assertTrue(ids.add(idGenerator.nextId()));
    }
}
