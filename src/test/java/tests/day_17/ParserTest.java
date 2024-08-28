package tests.day_17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import solutions.day_17.Parser;
import solutions.day_17.PixelatedSpinLockParams;

class ParserTest {

    @Test
    void parseLines() {
        final var INPUT = """
                2017
                3""";
        final var actual = Parser.parseLines(INPUT);
        final var EXPECTED = new PixelatedSpinLockParams(2017, 3);
        Assertions.assertEquals(actual, EXPECTED);
    }
}