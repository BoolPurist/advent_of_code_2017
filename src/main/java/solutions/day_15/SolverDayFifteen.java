package solutions.day_15;

import solutions.ProvidesPuzzleSolution;

public class SolverDayFifteen implements ProvidesPuzzleSolution {

    public static final long A_MULTIPLY_FACTOR = 16807;
    public static final long B_MULTIPLY_FACTOR = 48271;
    public static final int HOW_MANY_TIMES = 40 * ((int) Math.pow(10, 6));
    private static final long NUMBER_BITS_JUDGING_MASK = (((long) Math.pow(2, 16)) - 1);
    private final GeneratorStartingValue judge;

    public SolverDayFifteen(GeneratorStartingValue judge) {
        this.judge = judge;
    }


    public int howManyTimesNumberBitsMatch() {
        var generatorA = new Generator(judge.forA(), A_MULTIPLY_FACTOR, HOW_MANY_TIMES);
        var generatorB = new Generator(judge.forB(), B_MULTIPLY_FACTOR, HOW_MANY_TIMES);
        var howManyTimesTheSame = 0;
        while (generatorA.hasNext() && generatorB.hasNext()) {
            final var currentAValue = generatorA.next() & NUMBER_BITS_JUDGING_MASK;
            final var currentBValue = generatorB.next() & NUMBER_BITS_JUDGING_MASK;
            if (currentAValue == currentBValue) {
                howManyTimesTheSame++;
            }

        }
        return howManyTimesTheSame;
    }

    @Override
    public String produce() {
        final var numberOfMatches = howManyTimesNumberBitsMatch();

        return String.valueOf(numberOfMatches);
    }
}
