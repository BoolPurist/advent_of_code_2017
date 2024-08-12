package tests;

import commons.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.GivenTask;
import solutions.day_9.ParserDayNine;

import java.util.stream.Stream;

class DayNineTest {

    //    {}, score of 1.
//    {{{}}}, score of 1 + 2 + 3 = 6.
//    {{},{}}, score of 1 + 2 + 2 = 5.
//    {{{},{},{{}}}}, score of 1 + 2 + 3 + 3 + 3 + 4 = 16.
//    {<a>,<a>,<a>,<a>}, score of 1.
//    {{<ab>},{<ab>},{<ab>},{<ab>}}, score of 1 + 2 + 2 + 2 + 2 = 9.
//    {{<!!>},{<!!>},{<!!>},{<!!>}}, score of 1 + 2 + 2 + 2 + 2 = 9.
//    {{<a!>},{<a!>},{<a!>},{<ab>}}, score of 1 + 2 = 3. 
    static Stream<Arguments> groupScoreOfInput() {
        return Stream.of(
                Arguments.arguments("{}", "1"),
                Arguments.arguments("{{{}}}", "6"),
                Arguments.arguments("{{},{}}", "5"),
                Arguments.arguments("{<a>,<a>,<a>,<a>}", "1"),
                Arguments.arguments("{{<ab>},{<ab>},{<ab>},{<ab>}}", "9"),
                Arguments.arguments("{{<!!>},{<!!>},{<!!>},{<!!>}}", "9"),
                Arguments.arguments("{{<a!>},{<a!>},{<a!>},{<ab>}}", "3")
        );
    }

    //<>, 0 characters.
    //<random characters>, 17 characters.
    //<<<<>, 3 characters.
    //    <{!>}>, 2 characters.
    //<!!>, 0 characters.
    //<!!!>>, 0 characters.
    //    <{o"i!a,<{i<a>, 10 characters.
    static Stream<Arguments> garbageCountInput() {
        return Stream.of(
                Arguments.arguments("<>", "0"),
                Arguments.arguments("<random characters>", "17"),
                Arguments.arguments("<<<<>", "3"),
                Arguments.arguments("<{!>}>", "2"),
                Arguments.arguments("<!!!>>", "0"),
                Arguments.arguments("<{o\"i!a,<{i<a>", "10")
        );
    }

    @ParameterizedTest
    @MethodSource("groupScoreOfInput")
    void shouldCalcGroupScore(String input, String expected) {
        Utils.assertCase(input, new ParserDayNine(), GivenTask.FIRST, expected);
    }

    @ParameterizedTest
    @MethodSource("garbageCountInput")
    void shouldCalcGarbageCount(String input, String expected) {
        Utils.assertCase(input, new ParserDayNine(), GivenTask.SECOND, expected);
    }
}
