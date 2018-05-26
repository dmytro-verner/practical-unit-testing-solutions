package chapter_5.exercise_5_7_2;

import org.junit.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class RaceResultsServiceTests {

    private Logger logger = mock(Logger.class);
    private RaceResultsService raceResultsService = new RaceResultsService(logger);

    private Client clientA = mock(Client.class, "clientA");
    private Client clientB = mock(Client.class, "clientB");

    @Test
    public void multipleSubscribedClientsShouldReceiveMessages(){
        Message horseRacesMessage = mock(Message.class);
        when(horseRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.HORSE_RACES);

        raceResultsService.addSubscription(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.addSubscription(clientB, SubscriptionCategory.HORSE_RACES);
        raceResultsService.send(horseRacesMessage);

        verify(clientA).receive(horseRacesMessage);
        verify(clientB).receive(horseRacesMessage);
    }

    @Test
    public void unsubscribedClientShouldNotReceiveMessages(){
        Message horseRacesMessage = mock(Message.class);
        when(horseRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.HORSE_RACES);

        raceResultsService.addSubscription(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.removeSubscriber(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.send(horseRacesMessage);

        verify(clientA, never()).receive(horseRacesMessage);
    }

    @Test
    public void notSubscribedClientsDoNotReceiveMessages(){
        Message f1RacesMessage = mock(Message.class);
        when(f1RacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.F1_RACES);

        raceResultsService.send(f1RacesMessage);

        verify(clientA, never()).receive(f1RacesMessage);
        verify(clientB, never()).receive(f1RacesMessage);
    }

    @Test
    public void shouldSentOnlyOneMessageToMultiSubscriber(){
        Message horseRacesMessage = mock(Message.class);
        when(horseRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.HORSE_RACES);

        raceResultsService.addSubscription(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.addSubscription(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.send(horseRacesMessage);

        verify(clientA).receive(horseRacesMessage);
    }

    @Test
    public void clientShouldReceiveMessageOnSubscribedCategory(){
        Message horseRacesMessage = mock(Message.class);
        when(horseRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.HORSE_RACES);

        raceResultsService.addSubscription(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.send(horseRacesMessage);

        verify(clientA).receive(horseRacesMessage);
    }

    @Test
    public void clientShouldReceiveMessagesOnSubscribedCategoriesOnly(){
        Message horseRacesMessage = mock(Message.class);
        Message f1RacesMessage = mock(Message.class);
        Message boatRacesMessage = mock(Message.class);

        when(horseRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.HORSE_RACES);
        when(f1RacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.F1_RACES);
        when(boatRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.BOAT_RACES);

        raceResultsService.addSubscription(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.addSubscription(clientA, SubscriptionCategory.F1_RACES);
        raceResultsService.send(horseRacesMessage);
        raceResultsService.send(f1RacesMessage);
        raceResultsService.send(boatRacesMessage);

        verify(clientA).receive(horseRacesMessage);
        verify(clientA).receive(f1RacesMessage);
        verify(clientA, never()).receive(boatRacesMessage);
    }

    @Test
    public void resubscribedClientAfterUnsubscriptionReceivesMessage(){
        Message boatRacesMessage = mock(Message.class);
        when(boatRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.BOAT_RACES);

        raceResultsService.addSubscription(clientA, SubscriptionCategory.BOAT_RACES);
        raceResultsService.removeSubscriber(clientA, SubscriptionCategory.BOAT_RACES);
        raceResultsService.addSubscription(clientA, SubscriptionCategory.BOAT_RACES);
        raceResultsService.send(boatRacesMessage);

        verify(clientA).receive(boatRacesMessage);
    }

    @Test
    public void clientDoesNotGetMessageWhenItIsNotSent(){
        Message f1RacesMessage = mock(Message.class);
        when(f1RacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.F1_RACES);

        raceResultsService.addSubscription(clientA, SubscriptionCategory.F1_RACES);

        verify(clientA, never()).receive(f1RacesMessage);
    }

    @Test
    public void dateAndMessageAreLoggedWhenMessageIsSent(){
        LocalDate localDateNow = LocalDate.now();
        String logMessage = "message logged";

        Message horseRacesMessage = mock(Message.class);
        when(horseRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.HORSE_RACES);
        when(horseRacesMessage.getDate()).thenReturn(localDateNow);
        when(horseRacesMessage.getMessage()).thenReturn(logMessage);

        raceResultsService.addSubscription(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.send(horseRacesMessage);

        verify(logger).log(localDateNow, logMessage);
        verify(clientA).receive(horseRacesMessage);
    }

    @Test
    public void dateAndMessageAreNotLoggedWhenMessageIsNotSent(){
        LocalDate localDateNow = LocalDate.now();
        String logMessage = "message logged";

        Message horseRacesMessage = mock(Message.class);
        when(horseRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.HORSE_RACES);
        when(horseRacesMessage.getDate()).thenReturn(localDateNow);
        when(horseRacesMessage.getMessage()).thenReturn(logMessage);

        raceResultsService.addSubscription(clientA, SubscriptionCategory.HORSE_RACES);

        verify(logger, never()).log(localDateNow, logMessage);
    }

    @Test
    public void clientsGetMultipleMessagesOnSubscribedCategories(){
        Message horseRacesMessage = mock(Message.class);
        Message boatRacesMessage = mock(Message.class);
        when(horseRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.HORSE_RACES);
        when(boatRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.BOAT_RACES);

        raceResultsService.addSubscription(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.addSubscription(clientA, SubscriptionCategory.BOAT_RACES);
        raceResultsService.send(horseRacesMessage);
        raceResultsService.send(horseRacesMessage);
        raceResultsService.send(boatRacesMessage);

        verify(clientA, times(2)).receive(horseRacesMessage);
        verify(clientA).receive(boatRacesMessage);
    }

    @Test
    public void clientsGetMultipleMessagesOnlyOnSubscribedCategories(){
        Message horseRacesMessage = mock(Message.class);
        Message boatRacesMessage = mock(Message.class);
        when(horseRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.HORSE_RACES);
        when(boatRacesMessage.getSubscriptionCategory()).thenReturn(SubscriptionCategory.BOAT_RACES);

        raceResultsService.addSubscription(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.addSubscription(clientA, SubscriptionCategory.BOAT_RACES);
        raceResultsService.removeSubscriber(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.send(horseRacesMessage);
        raceResultsService.send(horseRacesMessage);
        raceResultsService.send(boatRacesMessage);
        raceResultsService.removeSubscriber(clientA, SubscriptionCategory.BOAT_RACES);
        raceResultsService.send(boatRacesMessage);

        verify(clientA, never()).receive(horseRacesMessage);
        verify(clientA).receive(boatRacesMessage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void unsubscribedClientTriesToUnsubscribe(){
        raceResultsService.removeSubscriber(clientA, SubscriptionCategory.F1_RACES);
    }

    @Test(expected = IllegalArgumentException.class)
    public void subscribedToDifferentCategoryDoesNotGetMessage(){
        raceResultsService.addSubscription(clientA, SubscriptionCategory.HORSE_RACES);
        raceResultsService.removeSubscriber(clientA, SubscriptionCategory.F1_RACES);
    }
}
