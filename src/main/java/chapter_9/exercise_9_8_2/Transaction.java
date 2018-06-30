package chapter_9.exercise_9_8_2;

public class Transaction {

    private long id;

    private State state;

    private boolean retryAllowed;

    private String message;

    private String billingId;

    public Transaction(long id, State state, boolean retryAllowed, String message, String billingId) {
        this.id = id;
        this.state = state;
        this.retryAllowed = retryAllowed;
        this.message = message;
        this.billingId = billingId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isRetryAllowed() {
        return retryAllowed;
    }

    public void setRetryAllowed(boolean retryAllowed) {
        this.retryAllowed = retryAllowed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBillingId() {
        return billingId;
    }

    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }
}
