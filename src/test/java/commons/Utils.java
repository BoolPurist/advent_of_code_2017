package commons;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParsePuzzleInput;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class Utils {
    private Utils() {
    }


    public static void assertCase(String input, ParsePuzzleInput parser, GivenTask task, String expected) {
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
