package solutions.day_14;

abstract class AbstractSolverDayFourteen {
    protected static final String PREFIX = "-";
    protected static final int HOW_MANY_TIMES = 128;
    protected final String unPrefixedHash;

    protected AbstractSolverDayFourteen(String unPrefixedHash) {
        this.unPrefixedHash = unPrefixedHash;
    }
}
