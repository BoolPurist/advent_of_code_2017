package commons;

import org.junit.jupiter.api.Assertions;
import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParseInput;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class Utils {
    private Utils() {
    }

    public static <T> void assertSet(Set<T> expected, Set<T> toTest) {
        Assertions.assertEquals(expected.size(), toTest.size());
        for (final var expectedToBeFound : expected) {
            Assertions.assertTrue(toTest.contains(expectedToBeFound));
        }
    }

    public static void assertCase(String input, ParseInput parser, GivenTask task, String expected) {
        try {
            var solution = parser.parse(input, task);
            var actual = solution.produce();
            assertEquals(expected, actual, String.format("Input: %s", input));
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> String tryGetResource(Class<T> someClass, String name) {
        try (var inputStream = someClass.getResourceAsStream(name)) {
            assertNotNull(inputStream);
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
