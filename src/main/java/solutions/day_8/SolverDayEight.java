package solutions.day_8;

import solutions.GivenTask;
import solutions.ProvidesPuzzleSolution;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

public class SolverDayEight implements ProvidesPuzzleSolution {
    private final GivenTask task;
    private final Set<String> registers;
    private final List<Instruction> instructions;

    public SolverDayEight(GivenTask task, Set<String> registers, List<Instruction> instructions) {
        this.task = task;
        this.registers = registers;
        this.instructions = instructions.stream().toList();
    }

    @Override
    public String produce() {
        return switch (task) {
            case FIRST -> solveTask1();
            case SECOND -> solveTask2();
        };
    }

    private String solveTask1() {

        var changedRegisters = simulateAllInstructions(_ -> {
        });
        final var max = changedRegisters.values().stream().max(Comparator.naturalOrder());
        assert max.isPresent();
        return String.valueOf(max.get());
    }

    private String solveTask2() {

        var consumer = new IntConsumer() {
            private int max = Integer.MIN_VALUE;

            public int getMax() {
                return max;
            }

            public void accept(int value) {
                max = Math.max(max, value);
            }
        };
        simulateAllInstructions(consumer);

        return String.valueOf(consumer.getMax());
    }

    private Map<String, Integer> simulateAllInstructions(IntConsumer onChangedRegister) {
        var changedRegisters = registers.stream().collect(Collectors.toMap(key -> key, _ -> 0));
        for (final var nextInstruction : this.instructions) {
            if (nextInstruction.condition().holds(changedRegisters::get)) {
                final var modification = nextInstruction.modifier().modifiedAmount();
                changedRegisters.computeIfPresent(nextInstruction.register(), (_, old) -> {
                    final var newValue = old + modification;
                    onChangedRegister.accept(newValue);
                    return newValue;
                });
            }
        }
        return changedRegisters;
    }


}
