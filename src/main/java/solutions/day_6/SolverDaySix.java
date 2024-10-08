package solutions.day_6;

import solutions.GivenTask;
import solutions.ProvidesPuzzleSolution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;

public class SolverDaySix implements ProvidesPuzzleSolution {
    private final List<Integer> data;
    private GivenTask task;

    public SolverDaySix(List<Integer> data, GivenTask task) {
        this.data = data;
        this.task = task;
    }

    private static Integer getMaxIndexOfBlocks(List<Integer> seq) {
        var max = seq.getFirst();
        var max_index = 0;
        for (int i = 1; i < seq.size(); i++) {
            final var currentElement = seq.get(i);

            if (currentElement > max) {
                max = currentElement;
                max_index = i;
            }
        }
        return max_index;
    }

    private static Integer wrappingIncrement(Integer number, Integer size) {
        return (number + 1) % size;
    }

    private static String joinBlocksToString(StringBuilder builder, List<Integer> toJoin) {
        for (Integer integer : toJoin) {
            builder.append(" ");
            builder.append(integer);
        }
        final var toReturn = builder.toString();
        builder.setLength(0);
        return toReturn;
    }

    @Override
    public String produce() {
        switch (task) {
            case GivenTask.FIRST:
                return findUpToRepetition((cycle, _) -> String.valueOf(cycle));
            default:
                return findUpToRepetition(new BiFunction<Integer, String, String>() {
                    private Integer cyclesAfterFistEncounter = 0;
                    private String firstEncounter = null;

                    public String apply(Integer cycle, String encounter) {

                        if (this.firstEncounter == null) {
                            this.firstEncounter = encounter;
                            this.cyclesAfterFistEncounter = cycle;
                            return null;
                        } else if (this.firstEncounter.equals(encounter)) {
                            final var cycleNumberAfterSecond = cycle - this.cyclesAfterFistEncounter;
                            return String.valueOf(cycleNumberAfterSecond);
                        } else {
                            return null;
                        }
                    }
                });
        }
    }

    private String findUpToRepetition(BiFunction<Integer, String, String> onFoundSame) {

        var currentBuffer = new ArrayList<>(this.data);
        if (currentBuffer.isEmpty()) {
            return "0";
        }
        var max_index = getMaxIndexOfBlocks(currentBuffer);

        // Loop
        var savedBlockStates = new HashSet<String>();
        var blockCycle = 0;
        var startBlockIndex = max_index;
        var setKeyBuilder = new StringBuilder();

        final var bufferSize = currentBuffer.size();
        while (true) {
            blockCycle++;
            var leftSteps = currentBuffer.get(startBlockIndex);
            currentBuffer.set(startBlockIndex, 0);

            for (int i = wrappingIncrement(startBlockIndex, bufferSize); 0 < leftSteps; i = wrappingIncrement(i,
                    bufferSize)) {
                leftSteps--;
                final var oldValue = currentBuffer.get(i);
                final var newValue = oldValue + 1;
                currentBuffer.set(i, newValue);
            }

            final var blockStateAfterCycle = joinBlocksToString(setKeyBuilder, currentBuffer);
            if (savedBlockStates.contains(blockStateAfterCycle)) {
                final var applied = onFoundSame.apply(blockCycle, blockStateAfterCycle);
                if (applied != null) {
                    return applied;
                }
            } else {
                savedBlockStates.add(blockStateAfterCycle);
            }
            startBlockIndex = getMaxIndexOfBlocks(currentBuffer);
        }
    }

}
