import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import solutions.GivenTask;
import solutions.day_8.*;

import java.util.stream.Stream;

class DayEightTest {
    private static final String TEST_INPUT = """
            b inc 5 if a > 1
            a inc 1 if b < 5
            c dec -10 if a >= 1
            c inc -20 if c == 10
            """;

    static Stream<Arguments> instructionsToParse() {
        return Stream.of(Arguments.arguments(
                        "b inc 5 if a > 1",
                        new Instruction(
                                "b",
                                new ModifierOperation(ModifierOperationKind.INCREMENT, 5),
                                new OperationCondition("a", ConditionKind.GREATER, 1)
                        )
                ), Arguments.arguments(
                        "a inc 1 if b < 5",
                        new Instruction(
                                "a",
                                new ModifierOperation(ModifierOperationKind.INCREMENT, 1),
                                new OperationCondition("b", ConditionKind.LESS, 5)
                        )
                ),
                Arguments.arguments(
                        "c dec -10 if a >= 1",
                        new Instruction(
                                "c",
                                new ModifierOperation(ModifierOperationKind.DECREMENT, -10),
                                new OperationCondition("a", ConditionKind.GREATER_THAN, 1)
                        )
                ),
                Arguments.arguments(
                        "c inc -20 if c == 10",
                        new Instruction(
                                "c",
                                new ModifierOperation(ModifierOperationKind.INCREMENT, -20),
                                new OperationCondition("c", ConditionKind.EQUAL, 10)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("instructionsToParse")
    void shouldParseLineToInstruction(String line, Instruction expected) {
        final var actual = ParserDayEight.parseLine(line);
        Assertions.assertEquals(expected, actual.second());
    }

    @Test
    void shouldModifyRegistersCorrectly() {
        final var expected = "1";
        Utils.assertCase(TEST_INPUT, new ParserDayEight(), GivenTask.FIRST, expected);
    }

    @Test
    void shouldFindGreatestRegisterValueAtAnyTime() {
        final var expected = "10";
        Utils.assertCase(TEST_INPUT, new ParserDayEight(), GivenTask.SECOND, expected);
    }
}
