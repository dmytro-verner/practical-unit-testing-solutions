package chapter_4.exercise_4_10_2;

import java.util.ArrayList;
import java.util.List;

public class RegexDigitsExtractor {

    /**
     * Extracts three or more consecutive digits
     * @param str input string to have digits extracted
     * @return list of extracted integers from the passed string
     */
    public List<String> extractThreeOrMoreConsecutiveNumbers(String str) {
        if(str == null)
            throw new IllegalArgumentException();
        if(str.equals(""))
            return new ArrayList<String>();

        List<String> result = new ArrayList<String>();
        char[] chars = str.toCharArray();
        StringBuilder consecutiveNumbers = new StringBuilder();
        for(char c : chars){
            if(Character.isDigit(c)) {
                consecutiveNumbers.append(c);
            }
            else if(consecutiveNumbers.length() < 3) {
                consecutiveNumbers = new StringBuilder();
            }
            else {
                result.add(consecutiveNumbers.toString());
                consecutiveNumbers = new StringBuilder();
            }
        }

        return result;
    }
}
