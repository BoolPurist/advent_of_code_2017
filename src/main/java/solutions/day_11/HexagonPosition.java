package solutions.day_11;

public record HexagonPosition(double x, double y) {
    public static HexagonPosition zero() {
        return new HexagonPosition(0.0, 0.0);
    }
}
