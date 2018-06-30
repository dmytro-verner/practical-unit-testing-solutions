package chapter_10.exercise_10_7_2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTest {

    private Stack<Integer> stack = new Stack<>();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIAEOnPoppingEmptyStack() {
        stack.pop();
    }

    @Test
    public void shouldPopSinglePushedElement() {
        stack.push(1);

        assertEquals(1, stack.pop().intValue());
    }

    @Test
    public void shouldPopLastAddedElement() {
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop().intValue());
    }

    @Test
    public void shouldBeAbleToPushEqualElements() {
        stack.push(2);
        stack.push(2);

        assertEquals(2, stack.pop().intValue());
        assertEquals(2, stack.pop().intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIAEOnPushingNull() {
        stack.push(null);
    }
}
