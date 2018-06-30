package chapter_7.exercise_7_9_1.legacy;

import chapter_7.exercise_7_9_1.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LegacyEmailServer.class, LegacyMailClient.class})
public class LegacyMailClientTest {

    @Test
    public void shouldSendEmailWithSpecifiedParameters() throws Exception {
        LegacyMailClient legacyMailClient = new LegacyMailClient();
        Email email = mock(Email.class);
        mockStatic(LegacyEmailServer.class);

        String address = "Wide Street";
        String title = "Great time";
        String body = "Hi guys!, It was really great";

        PowerMockito.whenNew(Email.class).withArguments(address, title, body).thenReturn(email);
        legacyMailClient.sendMail(address, title, body);

        PowerMockito.verifyStatic(LegacyEmailServer.class, times(1));
        LegacyEmailServer.sendMail(email);
    }
}
