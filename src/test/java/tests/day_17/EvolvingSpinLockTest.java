package tests.day_17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.day_17.EvolvingSpinLock;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

final class EvolvingSpinLockTest {
    static Stream<TimesCycleWithStateTestCase> nTimesCycleTestCases() {
        final var expected = List.of(
                "(0)",
                "0 (1)",
                "0 (2) 1",
                "0 2 (3) 1",
                "0 2 (4) 3 1",
                "0 (5) 2 4 3 1",
                "0 5 2 4 3 (6) 1",
                "0 5 (7) 2 4 3 6 1",
                "0 5 7 2 4 3 (8) 6 1",
                "0 (9) 5 7 2 4 3 8 6 1",
                "0 9 5 7 2 (10) 4 3 8 6 1",
                "0 9 5 7 2 10 4 3 8 (11) 6 1",
                "0 (12) 9 5 7 2 10 4 3 8 11 6 1",
                "0 12 9 5 7 (13) 2 10 4 3 8 11 6 1",
                "0 12 9 5 7 13 2 10 4 (14) 3 8 11 6 1",
                "0 12 9 5 7 13 2 10 4 14 3 8 11 (15) 6 1",
                "0 (16) 12 9 5 7 13 2 10 4 14 3 8 11 15 6 1",
                "0 16 12 9 5 (17) 7 13 2 10 4 14 3 8 11 15 6 1",
                "0 16 12 9 5 17 7 13 2 (18) 10 4 14 3 8 11 15 6 1",
                "0 16 12 9 5 17 7 13 2 18 10 4 14 (19) 3 8 11 15 6 1",
                "0 16 12 9 5 17 7 13 2 18 10 4 14 19 3 8 11 (20) 15 6 1",
                "0 16 12 9 5 17 7 13 2 18 10 4 14 19 3 8 11 20 15 6 1 (21)",
                "0 16 12 (22) 9 5 17 7 13 2 18 10 4 14 19 3 8 11 20 15 6 1 21",
                "0 16 12 22 9 5 17 (23) 7 13 2 18 10 4 14 19 3 8 11 20 15 6 1 21",
                "0 16 12 22 9 5 17 23 7 13 2 (24) 18 10 4 14 19 3 8 11 20 15 6 1 21",
                "0 16 12 22 9 5 17 23 7 13 2 24 18 10 4 (25) 14 19 3 8 11 20 15 6 1 21",
                "0 16 12 22 9 5 17 23 7 13 2 24 18 10 4 25 14 19 3 (26) 8 11 20 15 6 1 21",
                "0 16 12 22 9 5 17 23 7 13 2 24 18 10 4 25 14 19 3 26 8 11 20 (27) 15 6 1 21",
                "0 16 12 22 9 5 17 23 7 13 2 24 18 10 4 25 14 19 3 26 8 11 20 27 15 6 1 (28) 21",
                "0 16 (29) 12 22 9 5 17 23 7 13 2 24 18 10 4 25 14 19 3 26 8 11 20 27 15 6 1 28 21",
                "0 16 29 12 22 9 (30) 5 17 23 7 13 2 24 18 10 4 25 14 19 3 26 8 11 20 27 15 6 1 28 21"

        );
        return IntStream.range(0, expected.size()).mapToObj(index -> new TimesCycleWithStateTestCase(index, expected.get(index)));

    }

    @ParameterizedTest
    @MethodSource("nTimesCycleTestCases")
    void cycleNTimesToCorrectState(TimesCycleWithStateTestCase given) {
        final var state = new EvolvingSpinLock(3);
        for (int i = 0; i < given.nTimes(); i++) {
            state.nextCycle();
        }

        final var actual = state.toString();
        Assertions.assertEquals(given.expectedState(), actual);
    }

    private record TimesCycleWithStateTestCase(int nTimes, String expectedState) {
    }

}