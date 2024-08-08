import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.GivenTask;
import solutions.day_7.ParserDaySeven;
import solutions.day_7.ProgramOfTower;

import java.util.List;
import java.util.stream.Stream;

public class DaySevenTest {
    static Stream<Arguments> stringToProgramInTower() {

        return Stream.of(Arguments.arguments("pbga (66)", new ProgramOfTower("pbga", 66, List.of())),
                Arguments.arguments("fwft (72) -> ktlj, cntj, xhth", new ProgramOfTower("fwft", 72, List.of("ktlj", "cntj", "xhth")))
        );
    }

    @ParameterizedTest
    @MethodSource("stringToProgramInTower")
    public void shouldParseLineForProgramInTower(String line, ProgramOfTower expectedResult) {
        final var actual = ParserDaySeven.parseLine(line);
        Assertions.assertEquals(expectedResult, actual);
    }

    @Test
    public void shouldFindRootProgramTask1() {

        final var input = """
                pbga (66)
                xhth (57)
                ebii (61)
                havc (66)
                ktlj (57)
                fwft (72) -> ktlj, cntj, xhth
                qoyq (66)
                padx (45) -> pbga, havc, qoyq
                tknk (41) -> ugml, padx, fwft
                jptl (61)
                ugml (68) -> gyxo, ebii, jptl
                gyxo (61)
                cntj (57)""";
        final var expected = "tknk";
        Utils.assertCase(input, new ParserDaySeven(), GivenTask.FIRST, expected);
    }

    @Test
    public void shouldFindUnbalancedProgramTask2() {

        final var input = """
                pbga (66)
                xhth (57)
                ebii (61)
                havc (66)
                ktlj (57)
                fwft (72) -> ktlj, cntj, xhth
                qoyq (66)
                padx (45) -> pbga, havc, qoyq
                tknk (41) -> ugml, padx, fwft
                jptl (61)
                ugml (68) -> gyxo, ebii, jptl
                gyxo (61)
                cntj (57)""";
        final var expected = "60";
        Utils.assertCase(input, new ParserDaySeven(), GivenTask.SECOND, expected);
    }
}