package tests.day_15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.day_15.Generator;
import solutions.day_15.SolverDayFifteen;
import utils.IteratorUtils;

import java.util.stream.Stream;

final class GeneratorTest {
    //    For example, suppose that for starting values, generator A uses 65, while generator B uses 8921. Then, the first five pairs of generated values are:
//
//            --Gen. A--  --Gen. B--
//            1092455     430625591
//            1181022009  1233683848
//            245556042   1431495498
//            1744312007  137874439
//            1352636452  285222916 
    static final long EXAMPLE_START_A = 65;
    static final long EXAMPLE_START_B = 8921;

    static Stream<StartHowManyTimesExpectedEndValue> testCases() {
        return Stream.of(
                StartHowManyTimesExpectedEndValue.caseForA(1, 1092455),
                StartHowManyTimesExpectedEndValue.caseForA(2, 1181022009),
                StartHowManyTimesExpectedEndValue.caseForA(3, 245556042),
                StartHowManyTimesExpectedEndValue.caseForA(4, 1744312007),
                StartHowManyTimesExpectedEndValue.caseForA(5, 1352636452),

                StartHowManyTimesExpectedEndValue.caseForB(1, 430625591),
                StartHowManyTimesExpectedEndValue.caseForB(2, 1233683848),
                StartHowManyTimesExpectedEndValue.caseForB(3, 1431495498),
                StartHowManyTimesExpectedEndValue.caseForB(4, 137874439),
                StartHowManyTimesExpectedEndValue.caseForB(5, 285222916)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void givenStartValueGenerateToEndValue(StartHowManyTimesExpectedEndValue testCase) {
        final var generator = testCase.createGenerator();

        final var actual = IteratorUtils.iteratorToReadonlyList(generator).getLast();

        Assertions.assertEquals(testCase.expectedEndValue, actual, () -> testCase.toString());
    }


    record StartHowManyTimesExpectedEndValue(long start, long productFactor, int howManyTimes, long expectedEndValue) {
        public static StartHowManyTimesExpectedEndValue caseForA(int howManyTimes, long expectedEndValue) {
            return new StartHowManyTimesExpectedEndValue(EXAMPLE_START_A, SolverDayFifteen.A_MULTIPLY_FACTOR, howManyTimes, expectedEndValue);
        }

        public static StartHowManyTimesExpectedEndValue caseForB(int howManyTimes, long expectedEndValue) {
            return new StartHowManyTimesExpectedEndValue(EXAMPLE_START_B, SolverDayFifteen.B_MULTIPLY_FACTOR, howManyTimes, expectedEndValue);
        }

        public Generator createGenerator() {
            return new Generator(start, productFactor, howManyTimes);
        }
    }
}