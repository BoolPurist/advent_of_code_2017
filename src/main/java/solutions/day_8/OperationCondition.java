package solutions.day_8;

import java.util.function.ToIntFunction;

public record OperationCondition(String left, ConditionKind kind, int right) {
    public boolean holds(ToIntFunction<String> mapper) {
        final var mappedLeft = mapper.applyAsInt(left);
        return switch (kind) {
            case LESS -> mappedLeft < right;
            case GREATER -> mappedLeft > right;
            case EQUAL -> mappedLeft == right;
            case NOT_EQUAL -> mappedLeft != right;
            case GREATER_THAN -> mappedLeft >= right;
            case LESS_THAN -> mappedLeft <= right;
        };
    }
}
