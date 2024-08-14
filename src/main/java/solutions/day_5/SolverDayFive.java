package solutions.day_5;

import solutions.GivenTask;
import solutions.ProducesSolution;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SolverDayFive implements ProducesSolution {
    private final List<Integer> data;
    private final GivenTask task;

    public SolverDayFive(List<Integer> data, GivenTask task) {
        this.data = data;
        this.task = task;
    }

    @Override
    public String produce() {
        final Function<Integer, Integer> stepMapping = task == GivenTask.FIRST ? e -> e + 1
                : e -> e < 3 ? e + 1 : e - 1;
        return goThroughInstructions(stepMapping);
    }

    private String goThroughInstructions(Function<Integer, Integer> stepMapping) {

        var toWorkOn = new ArrayList<Integer>();
        toWorkOn.addAll(this.data);
        final var listSize = toWorkOn.size();

        var currentIndex = 0;
        var count = 0;
        while (currentIndex < listSize) {
            count++;
            final var steps = toWorkOn.get(currentIndex);
            toWorkOn.set(currentIndex, stepMapping.apply(steps));
            currentIndex += steps;
            if (currentIndex < 0) {
                throw new RuntimeException(String.format("Current index became negative: Value = %n", currentIndex));
            }
        }
        return String.valueOf(count);
    }

}
