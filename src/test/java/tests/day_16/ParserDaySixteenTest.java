package tests.day_16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import solutions.day_16.ExchangeMove;
import solutions.day_16.ParserDaySixteen;
import solutions.day_16.PartnerMove;
import solutions.day_16.SpinMove;

final class ParserDaySixteenTest {

    @Test
    void parseSpinComponent() {
        final var INPUT = "s4";
        final var expected = new SpinMove(4);
        final var actual = ParserDaySixteen.parseForMove(INPUT);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parseIndexSwap() {
        final var INPUT = "x4/8";
        final var expected = new ExchangeMove(4, 8);
        final var actual = ParserDaySixteen.parseForMove(INPUT);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void parsePartnering() {
        final var INPUT = "pa/b";
        final var expected = new PartnerMove('a', 'b');
        final var actual = ParserDaySixteen.parseForMove(INPUT);

        Assertions.assertEquals(expected, actual);
    }
}
