package chapter_3.exercise_3_11_2;

public class StringUtil {
    public static String reverse(String s) {
        if(s == null)
            throw new IllegalArgumentException("The argument can't be null.");

        if(s.equals(""))
            return "";

        StringBuilder reversedString = new StringBuilder(s.length());
        for(int i = s.length() - 1; i >= 0; i--) {
            reversedString.append(s.charAt(i));
        }
        return reversedString.toString();
    }
}
