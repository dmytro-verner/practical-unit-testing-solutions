package chapter_4.exercise_4_10_1;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class PasswordValidatorTest {

    private PasswordValidator passwordValidator = new PasswordValidator();

    private final String ANY_VALID_PASSWORD = "Test1234#_";

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIAEOnNull(){
        passwordValidator.isValid(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIAEOnEmptyString(){
        passwordValidator.isValid("");
    }

    @Test
    public void password10CharactersLongIsValid(){
        Assert.assertTrue(passwordValidator.isValid(ANY_VALID_PASSWORD));
    }

    private static Object[] otherThan10CharactersLong(){
        return new Object[] {
                new Object[] {"Pas1234#_"},
                new Object[] {"Pass1234#_1"}
        };
    }

    @Test
    @Parameters(method = "otherThan10CharactersLong")
    public void passwordOtherThan10CharactersLongIsInvalid(String otherThan10CharactersLong){
        Assert.assertFalse(passwordValidator.isValid(otherThan10CharactersLong));
    }

    @Test
    public void shouldNotAllowPasswordWith3AndLessDigits(){
        Assert.assertFalse(passwordValidator.isValid("Test123t#_"));
    }

    @Test
    public void shouldHaveAtLeast4Digits(){
        Assert.assertTrue(passwordValidator.isValid("Test1234#_"));
    }

    @Test
    public void shouldNotAllowPasswordWithoutHashSign(){
        Assert.assertFalse(passwordValidator.isValid("Test1234__"));
    }

    @Test
    public void shouldNotAllowPasswordWithoutUnderscoreSign(){
        Assert.assertFalse(passwordValidator.isValid("More0987#0"));
    }

    @Test
    public void shouldHaveUpperAndLowerCaseLetters(){
        Assert.assertTrue(passwordValidator.isValid("_#More5674"));
    }

    private static Object[] passwordsWithSpaces(){
        return new Object[] {
                new Object[] {" Pa1234#_0"},
                new Object[] {" Pa1234#_01"},
                new Object[] {"\nPa1234#_0"},
                new Object[] {"Pa1234 #_0"},
                new Object[] {"pA1234\n#_0"},
                new Object[] {"pA1234\n#_90"},
                new Object[] {"Pa1234#_90 "},
                new Object[] {"Pa1234\t#_9"},
                new Object[] {"Pa1234\t#_90"}
        };
    }

    @Test
    @Parameters(method = "passwordsWithSpaces")
    public void anySpacesAreNotAllowed(String passwordWithSpaces){
        Assert.assertFalse(passwordValidator.isValid(passwordWithSpaces));
    }

    private static Object[] passwordsStartingWithAllowedCharacters(){
        return new Object[] {
                new Object[] {"1Pa456_#90"},
                new Object[] {"_Pa456#890"},
                new Object[] {"#Pa456_890"},
                new Object[] {"#Pa456_890"},
                new Object[] {"Pa345_#890"},
                new Object[] {"aP345_#890"}
        };
    }

    @Test
    @Parameters(method = "passwordsStartingWithAllowedCharacters")
    public void shouldAllowLettersDigitsHashAndUnderscoreAsFirstCharacter(String passwordWithDifferentFirstCharacters){
        Assert.assertTrue(passwordValidator.isValid(passwordWithDifferentFirstCharacters));
    }

    private static Object[] passwordsWithNotAllowedSigns(){
        String correctPart = "Pa3456#_";
        return new Object[] {
                new Object[] {correctPart + "~`"},
                new Object[] {correctPart + "!@"},
                new Object[] {correctPart + "$%"},
                new Object[] {correctPart + "№:"},
                new Object[] {correctPart + "^&"},
                new Object[] {correctPart + "?*"},
                new Object[] {correctPart + "()"},
                new Object[] {correctPart + "-="},
                new Object[] {correctPart + "+\\"},
                new Object[] {correctPart + "/|"},
                new Object[] {correctPart + ";."},
                new Object[] {correctPart + "<>"}
        };
    }

    @Test
    @Parameters(method = "passwordsWithNotAllowedSigns")
    public void shouldNotAllowPasswordContainingOtherThanLettersDigitsHashUnderscore(String passwordWithInvalidCharacters){
        Assert.assertFalse(passwordWithInvalidCharacters + " contains illegal characters",
                passwordValidator.isValid(passwordWithInvalidCharacters));
    }

    private static Object[] passwordWithNonLatinSymbols(){
        String correctPart = "1234#_Pa";
        return new Object[] {
                new Object[] {correctPart + "月山"}, //mandarin
                new Object[] {correctPart + "رذ"}, //arabic
                new Object[] {correctPart + "たサ"}, //japanese
                new Object[] {correctPart + "аб"}, //russian
                new Object[] {correctPart + "сс"}, //russian
                new Object[] {correctPart + "їґ"}, //ukrainian
                new Object[] {correctPart + "äß"}, //german
                new Object[] {correctPart + "øæ"}, //danish
                new Object[] {correctPart + "ññ"}, //spanish
                new Object[] {correctPart + "कस"} //hindu
        };
    }

    @Test
    @Parameters(method = "passwordWithNonLatinSymbols")
    public void shouldNotAllowAnyNonLatinCharacter(String passwordWithNonLatinCharacter){
        Assert.assertFalse(passwordWithNonLatinCharacter + " contains illegal non latin symbols",
                passwordValidator.isValid(passwordWithNonLatinCharacter));
    }
}
