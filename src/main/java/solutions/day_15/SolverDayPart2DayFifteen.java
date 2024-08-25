package solutions.day_15;

import solutions.ProvidesPuzzleSolution;

public final class SolverDayPart2DayFifteen extends AbstractSolverDayFifteen implements ProvidesPuzzleSolution {
    public static final long MULTIPLE_A = 4;
    public static final long MULTIPLE_B = 8;
    private static final int HOW_MANY_TIMES = 5 * AbstractSolverDayFifteen.MILLIONS;


    public SolverDayPart2DayFifteen(GeneratorStartingValue judge) {
        super(judge);
    }

    private int howManyTimesNumberBitsMatch() {
        var generatorA = new Generator(judge.forA(), A_MULTIPLY_FACTOR, HOW_MANY_TIMES);
        var generatorB = new Generator(judge.forB(), B_MULTIPLY_FACTOR, HOW_MANY_TIMES);
        generatorA.setMultipleDivider(MULTIPLE_A);
        generatorB.setMultipleDivider(MULTIPLE_B);
        return super.howManyTimesThePairsMatch(generatorA, generatorB);
    }

    @Override
    public String produce() {
        final var judgeDecided = howManyTimesNumberBitsMatch();
        return String.valueOf(judgeDecided);
    }
}
