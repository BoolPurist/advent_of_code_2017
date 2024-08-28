package tests.day_17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.day_17.SolverPart2;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SolverPart2Test {
    static Stream<AfterZeroInCyclesTestCase> valueAfterZeroTestCases() {
        final var expected = List.of(1, 2, 2, 2, 5, 5, 5, 5, 9, 9, 9, 12, 12, 12, 12, 16);
        return IntStream.range(0, expected.size()).mapToObj(index -> new AfterZeroInCyclesTestCase(index + 1, expected.get(index)));
    }

    @ParameterizedTest
    @MethodSource("valueAfterZeroTestCases")
    void getFirstElementAfterManyCycle(AfterZeroInCyclesTestCase testCase) {
        final var actual = SolverPart2.calcAfterZeroInCycle(testCase.cycles(), 3);
        Assertions.assertEquals(testCase.expectedAfterZero(), actual);
    }
//                "0 (1)",
//                        "0 (2) 1",
//                        "0 2 (3) 1",
//                        "0 2 (4) 3 1",
//                        "0 (5) 2 4 3 1",
//                        "0 5 2 4 3 (6) 1",
//                        "0 5 (7) 2 4 3 6 1",
//                        "0 5 7 2 4 3 (8) 6 1",
//                        "0 (9) 5 7 2 4 3 8 6 1",
//                        "0 9 5 7 2 (10) 4 3 8 6 1",
//                        "0 9 5 7 2 10 4 3 8 (11) 6 1",
//                        "0 (12) 9 5 7 2 10 4 3 8 11 6 1",
//                        "0 12 9 5 7 (13) 2 10 4 3 8 11 6 1",
//                        "0 12 9 5 7 13 2 10 4 (14) 3 8 11 6 1",
//                        "0 12 9 5 7 13 2 10 4 14 3 8 11 (15) 6 1",
//                        "0 (16) 12 9 5 7 13 2 10 4 14 3 8 11 15 6 1",
//                        "0 16 12 9 5 (17) 7 13 2 10 4 14 3 8 11 15 6 1",
//                        "0 16 12 9 5 17 7 13 2 (18) 10 4 14 3 8 11 15 6 1",
//                        "0 16 12 9 5 17 7 13 2 18 10 4 14 (19) 3 8 11 15 6 1",
//                        "0 16 12 9 5 17 7 13 2 18 10 4 14 19 3 8 11 (20) 15 6 1",
    private record AfterZeroInCyclesTestCase(int cycles, int expectedAfterZero) {}
}
