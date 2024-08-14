package solutions.day_11;

public record HexagonPosition(double x, double y) {

    /**
     * @return origin point from which all hexagons are spawned
     * It is at (0, 0) => x = 0 and y = 0
     */
    public static HexagonPosition origin() {
        return new HexagonPosition(0.0, 0.0);
    }
}
