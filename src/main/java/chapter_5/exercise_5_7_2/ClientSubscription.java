package chapter_5.exercise_5_7_2;

import java.util.Objects;

public class ClientSubscription {

    private Client client;
    private SubscriptionCategory subscriptionCategory;

    public ClientSubscription(Client client, SubscriptionCategory subscriptionCategory) {
        this.client = client;
        this.subscriptionCategory = subscriptionCategory;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SubscriptionCategory getSubscriptionCategory() {
        return subscriptionCategory;
    }

    public void setSubscriptionCategory(SubscriptionCategory subscriptionCategory) {
        this.subscriptionCategory = subscriptionCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientSubscription that = (ClientSubscription) o;
        return Objects.equals(client, that.client) &&
                subscriptionCategory == that.subscriptionCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, subscriptionCategory);
    }
}
