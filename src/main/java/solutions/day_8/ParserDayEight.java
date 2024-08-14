package solutions.day_8;

import solutions.GivenTask;
import solutions.InvalidInputException;
import solutions.ParseInput;
import solutions.ProducesSolution;
import utils.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public final class ParserDayEight implements ParseInput {
    private static final Pattern lineRegex = Pattern.compile("(?<register>\\w+)\\s+(?<modifierOperator>inc|dec)\\s+(?<modifierAmount>-?\\d+)\\s+if\\s+(?<leftCond>\\w+)\\s+(?<condOperator>>|<|>=|<=|==|!=)\\s+(?<rightCond>-?\\d+)");

    public static Pair<Set<String>, Instruction> parseLine(String line) {
        final var matcher = lineRegex.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Line is not valid: %s", line));
        }
        final var register = matcher.group("register");
        final var modifierOperator = matcher.group("modifierOperator");
        final var modifierAmount = Integer.parseInt(matcher.group("modifierAmount"));
        final var leftCond = matcher.group("leftCond");
        final var condOperator = matcher.group("condOperator");
        final var rightCond = Integer.parseInt(matcher.group("rightCond"));
        final var foundNames = new HashSet<>(List.of(register, leftCond));
        final var modifierKind = ModifierOperationKind.fromText(modifierOperator);
        final var modifier = new ModifierOperation(modifierKind, modifierAmount);
        final var conditionalOperation = ConditionKind.parse(condOperator);
        final var condition = new OperationCondition(leftCond, conditionalOperation, rightCond);
        return new Pair<>(foundNames, new Instruction(register, modifier, condition));
    }

    @Override
    public ProducesSolution parse(String input, GivenTask task) throws InvalidInputException {
        var foundNames = new HashSet<String>();
        final var instructions = input.lines().map(line -> {
            final var parsed = parseLine(line);
            foundNames.addAll(parsed.first());
            return parsed.second();
        }).toList();
        return new SolverDayEight(task, foundNames, instructions);
    }
}
