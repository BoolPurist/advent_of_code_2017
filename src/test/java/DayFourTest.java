import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.GivenTask;
import solutions.day_4.ParserDayFour;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DayFourTest {
    //
    // Examples for task 1
    // aa bb cc dd ee is valid.
    // aa bb cc dd aa is not valid - the word aa appears more than once.
    // aa bb cc dd aaa is valid - aa and aaa count as different words.
    //
    // Examples for task 2
    //
    // abcde fghij is a valid passphrase.
    // abcde xyz ecdab is not valid - the letters from the third word can be
    // rearranged to form the first word.
    // a ab abc abd abf abj is a valid passphrase, because all letters need to be
    // used when forming another word.
    // iiii oiii ooii oooi oooo is valid.
    // oiii ioii iioi iiio is not valid - any of these words can be rearranged to
    // form any other word.

    static Stream<Arguments> solutionsFromExampleTaskTwo() {
        return Stream.of(
                arguments("abcde fghij", "1"),
                arguments("abcde xyz ecdab", "0"),
                arguments("a ab abc abd abf abj", "1"),
                arguments("iiii oiii ooii oooi oooo", "1"),
                arguments("oiii ioii iioi iiio", "0"));
    }

    @Test
    public void detectValidityOfLetterSequence() {
        var input = "aa bb cc dd ee\naa bb cc dd aa\naa bb cc dd aaa";
        var expected = "2";
        Utils.assertCase(input, new ParserDayFour(), GivenTask.FIRST, expected);

    }

    @Test
    public void detectValidityOfLetterSequenceTask2() {
        var input = Utils.tryGetResource(getClass(), "day_4_t2_examples.txt");
        ;
        var expected = "3";
        Utils.assertCase(input, new ParserDayFour(), GivenTask.SECOND, expected);

    }

    @ParameterizedTest
    @MethodSource("solutionsFromExampleTaskTwo")
    public void detectNonAnagrams(String input, String expected) {
        Utils.assertCase(input, new ParserDayFour(), GivenTask.SECOND, expected);
    }

}
