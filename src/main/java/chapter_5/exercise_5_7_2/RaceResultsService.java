package chapter_5.exercise_5_7_2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class RaceResultsService {

    private Collection<ClientSubscription> subscriptions = new HashSet<>();
    private Logger logger;

    public RaceResultsService(Logger logger) {
        this.logger = logger;
    }

    public void send(Message message) {
        logger.log(message.getDate(), message.getMessage());
        subscriptions.stream()
                .filter(s -> s.getSubscriptionCategory() == message.getSubscriptionCategory())
                .forEach(s -> s.getClient().receive(message));
    }

    public void removeSubscriber(Client client, SubscriptionCategory subscriptionCategory) {
        Optional<ClientSubscription> clientSubscription = subscriptions
                .stream()
                .filter(s -> s.getClient().equals(client) && s.getSubscriptionCategory() == subscriptionCategory)
                .findFirst();
        if(!clientSubscription.isPresent())
            throw new IllegalArgumentException();
        subscriptions.remove(clientSubscription.get());
    }

    public void addSubscription(Client client, SubscriptionCategory subscriptionCategory) {
        subscriptions.add(new ClientSubscription(client, subscriptionCategory));
    }
}
