package chapter_9.exercise_9_8_2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionTest {

    @Test
    public void constructorShouldSetAllFieldsOfTransaction() {
        long id = 123212L;
        State state = State.CANCELLED;
        boolean isRetryAllowed = true;
        String message = "Nice payment";
        String billingId = "432413431";

        Transaction transaction = new TransactionBuilder().withId(id)
                .withState(state)
                .withRetryAllowed(isRetryAllowed)
                .withMessage(message)
                .withBillingId(billingId)
                .build();

        assertEquals(id, transaction.getId());
        assertEquals(state, transaction.getState());
        assertEquals(isRetryAllowed, transaction.isRetryAllowed());
        assertEquals(message, transaction.getMessage());
        assertEquals(billingId, transaction.getBillingId());
    }
}
