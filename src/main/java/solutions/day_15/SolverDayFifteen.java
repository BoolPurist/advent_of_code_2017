package solutions.day_15;

import solutions.ProvidesPuzzleSolution;

public final class SolverDayFifteen extends AbstractSolverDayFifteen implements ProvidesPuzzleSolution {

    private static final int HOW_MANY_TIMES = 40 * AbstractSolverDayFifteen.MILLIONS;

    public SolverDayFifteen(GeneratorStartingValue judge) {
        super(judge);
    }


    public int howManyTimesNumberBitsMatch() {
        var generatorA = new Generator(judge.forA(), A_MULTIPLY_FACTOR, HOW_MANY_TIMES);
        var generatorB = new Generator(judge.forB(), B_MULTIPLY_FACTOR, HOW_MANY_TIMES);
        return super.howManyTimesThePairsMatch(generatorA, generatorB);
    }

    @Override
    public String produce() {
        final var numberOfMatches = howManyTimesNumberBitsMatch();

        return String.valueOf(numberOfMatches);
    }
}
