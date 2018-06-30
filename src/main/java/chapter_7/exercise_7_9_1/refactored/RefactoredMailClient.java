package chapter_7.exercise_7_9_1.refactored;

import chapter_7.exercise_7_9_1.Email;

public class RefactoredMailClient {

    private RefactoredEmailServer refactoredEmailServer;

    public RefactoredMailClient(RefactoredEmailServer refactoredEmailServer) {
        this.refactoredEmailServer = refactoredEmailServer;
    }

    public void sendMail(Email email) {
        refactoredEmailServer.sendMail(email);
    }
}
