package commons;

import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Set;

public class ComplexAssertions {

    public static <T> void assertList(List<T> expected, List<T> toTest) {
        Assertions.assertEquals(expected.size(), toTest.size());
        for (int i = 0; i < expected.size(); i++) {
            final var nextExpected = expected.get(i);
            final var nextActual = toTest.get(i);
            final var isEqual = nextExpected.equals(nextActual);
            final var indexToPrint = i;
            Assertions.assertTrue(
                    isEqual,
                    () -> String.format("Not equal at index %d with expected element %s and actual element %s", indexToPrint, nextExpected, nextActual)
            );
        }
    }

    public static <T> void assertSet(Set<T> expected, Set<T> toTest) {
        Assertions.assertEquals(expected.size(), toTest.size());
        for (final var expectedToBeFound : expected) {
            Assertions.assertTrue(
                    toTest.contains(expectedToBeFound),
                    () -> String.format("Expected  (%s) not found in actual", expectedToBeFound)
            );
        }
    }
}
