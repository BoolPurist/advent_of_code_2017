package solutions.day_16;

import solutions.ProvidesPuzzleSolution;

import java.util.List;

public final class SolverDaySixteen extends AbstractSolverDaySixteen implements ProvidesPuzzleSolution {

    public SolverDaySixteen(List<DanceMove> moves, int howManyTimes) {
        super(moves, howManyTimes);
    }

    @Override
    public String produce() {
        final var programs = new DancingPrograms(howManyTimes);
        for (var move : moves) {
            move.applyMoveTo(programs);
        }
        return programs.toString();
    }
}
