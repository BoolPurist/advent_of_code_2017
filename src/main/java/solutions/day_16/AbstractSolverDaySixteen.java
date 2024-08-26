package solutions.day_16;

import java.util.List;

public abstract class AbstractSolverDaySixteen {
    protected final List<DanceMove> moves;
    protected final int howManyTimes;

    protected AbstractSolverDaySixteen(List<DanceMove> moves, int howManyTimes) {
        this.moves = moves;
        this.howManyTimes = howManyTimes;
    }
}
