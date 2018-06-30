package chapter_7.exercise_7_9_1.refactored;

import chapter_7.exercise_7_9_1.Email;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RefactoredMailClientTest {

    @Test
    public void shouldSendEmailWithSpecifiedParameters() {
        RefactoredEmailServer refactoredEmailServer = mock(RefactoredEmailServer.class);
        Email email = mock(Email.class);
        RefactoredMailClient refactoredMailClient = new RefactoredMailClient(refactoredEmailServer);

        refactoredMailClient.sendMail(email);

        verify(refactoredEmailServer).sendMail(email);
    }
}
