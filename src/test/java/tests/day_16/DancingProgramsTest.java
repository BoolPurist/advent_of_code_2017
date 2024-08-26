package tests.day_16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.day_16.DancingPrograms;
import solutions.day_16.NotFoundProgramException;

import java.util.stream.Stream;

final class DancingProgramsTest {
    static Stream<CreateInThisInterval> creatingTestCases() {
        return Stream.of(
                new CreateInThisInterval(5, "abcde"),
                new CreateInThisInterval(1, "a"),
                new CreateInThisInterval(8, "abcdefgh")
        );
    }

    //    x3/4, swapping the last two programs: eabdc
    static Stream<SwapHere> swapTestCases() {
        return Stream.of(
                new SwapHere(5, 3, 4, "abced"),
                new SwapHere(5, 0, 0, "abcde"),
                new SwapHere(5, 1, 3, "adcbe")
        );
    }

    static Stream<IllegalSwapOperations> illegalSwapTestCases() {
        return Stream.of(
                new IllegalSwapOperations(5, 5, 4, "Left index (5) out of bounds. Valid range is between 0 and 5"),
                new IllegalSwapOperations(5, -1, 0, "Left index (-1) out of bounds. Valid range is between 0 and 5"),
                new IllegalSwapOperations(5, 0, -1, "Right index (-1) out of bounds. Valid range is between 0 and 5"),
                new IllegalSwapOperations(5, 0, 5, "Right index (5) out of bounds. Valid range is between 0 and 5")
        );
    }

    static Stream<PartnerThese> partneringTestCases() {
        return Stream.of(
                new PartnerThese(5, 'a', 'b', "bacde"),
                new PartnerThese(5, 'e', 'b', "aecdb"),
                new PartnerThese(5, 'a', 'a', "abcde")
        );
    }

    static Stream<NotFoundLeftOrRight> notFoundLeftOrRightTestCases() {
        return Stream.of(
                new NotFoundLeftOrRight(5, 'z', 'b', "There is no program called (z)"),
                new NotFoundLeftOrRight(5, 'b', 'y', "There is no program called (y)")
        );
    }

    static Stream<SpinTimesCases> spinTimesTestCases() {
        return Stream.of(
                new SpinTimesCases(5, 0, "abcde"),
                new SpinTimesCases(5, 1, "eabcd"),
                new SpinTimesCases(5, 3, "cdeab"),
                new SpinTimesCases(5, 5, "abcde")
        );

    }

    @ParameterizedTest
    @MethodSource("spinTimesTestCases")
    void spinningThatManyTimes(SpinTimesCases given) {
        final var toWorkWith = new DancingPrograms(given.howMany());

        toWorkWith.spinSoManyTimes(given.howManySpins());

        final var actualState = toWorkWith.toString();
        Assertions.assertEquals(given.expectedOutput(), actualState);
    }

    @ParameterizedTest
    @MethodSource("notFoundLeftOrRightTestCases")
    void throwExceptionIfLeftOrRightIsNotFound(NotFoundLeftOrRight given) {
        final var toWorkWith = new DancingPrograms(given.howMany());

        final var actualMessage = Assertions.assertThrows(
                        NotFoundProgramException.class,
                        () -> toWorkWith.swapByName(given.left, given.right))
                .getMessage();

        Assertions.assertEquals(given.expectedMessage(), actualMessage);
    }

    @ParameterizedTest
    @MethodSource("partneringTestCases")
    void creatCorrectSequence(PartnerThese given) {
        final var toWorkWith = new DancingPrograms(given.howMany());

        toWorkWith.swapByName(given.left, given.right);

        final var expected = given.expectedOutput();
        final var actual = toWorkWith.toString();
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("creatingTestCases")
    void creatCorrectSequence(CreateInThisInterval given) {
        final var actual = new DancingPrograms(given.howMany()).toString();
        final var expected = given.expectedOutput();
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("swapTestCases")
    void creatCorrectSequence(SwapHere given) {
        final var toWorkWith = new DancingPrograms(given.howMany());

        toWorkWith.swap(given.left, given.right);

        final var expected = given.expectedOutput();
        final var actual = toWorkWith.toString();
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("illegalSwapTestCases")
    void catchIllegalSwap(IllegalSwapOperations given) {
        final var toWorkWith = new DancingPrograms(given.howMany());

        final var actualThrown = Assertions.assertThrows(IllegalArgumentException.class, () -> toWorkWith.swap(given.left, given.right));
        Assertions.assertEquals(given.expectedMessage, actualThrown.getMessage());
    }

    record IllegalSwapOperations(int howMany, int left, int right, String expectedMessage) {
    }

    record NotFoundLeftOrRight(int howMany, char left, char right, String expectedMessage) {
    }

    record CreateInThisInterval(int howMany, String expectedOutput) {
    }

    record SwapHere(int howMany, int left, int right, String expectedOutput) {
    }

    record PartnerThese(int howMany, char left, char right, String expectedOutput) {
    }

    record SpinTimesCases(int howMany, int howManySpins, String expectedOutput) {
    }
}
