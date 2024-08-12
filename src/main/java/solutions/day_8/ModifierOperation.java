package solutions.day_8;

public record ModifierOperation(ModifierOperationKind kind, Integer value) {

    public Integer modifiedAmount() {
        return switch (kind) {
            case INCREMENT -> value;
            case DECREMENT -> -value;
        };
    }
}
