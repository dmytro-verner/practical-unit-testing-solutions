package chapter_4.exercise_4_10_1;

public class PasswordValidator {

    /**
     * Validates whether the passed string is a valid password
     * @param password Should have a mix of uppercase and lowercase letters. At least 4 digits. At least one hash and underscore signs
     *                 Shouldn't have any other characters and spaces
     * @return whether the string is a valid password
     * @throws IllegalArgumentException if passed string is null or empty
     */
    public boolean isValid(String password) {
        if(password == null || password.equals(""))
            throw new IllegalArgumentException();

        if(password.length() != 10){
            return false;
        }

        if (password.replaceAll("\\D", "").length() < 4)
            return false;

        if(!password.contains("#"))
            return false;

        if(!password.contains("_"))
            return false;

        if(!password.matches("[\\w#]+"))
            return false;

        boolean hasUpperCaseLetters = !password.equals(password.toLowerCase());
        boolean hasLowerCaseLetters = !password.equals(password.toUpperCase());
        if(!hasLowerCaseLetters || !hasUpperCaseLetters)
            return false;

        if(!password.replaceAll("\\s+", "").equals(password))
            return false;

        return true;
    }
}
