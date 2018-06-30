package chapter_7.exercise_7_9_1.legacy;

import chapter_7.exercise_7_9_1.Email;

public class LegacyMailClient {

    public void sendMail(String address, String title, String body) {
        Email email = new Email(address, title, body);
        LegacyEmailServer.sendMail(email);
    }
}
