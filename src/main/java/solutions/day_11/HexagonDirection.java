package solutions.day_11;

public enum HexagonDirection {
    NORTH(0, 1),
    NORTH_WEST(-0.5, 0.5),
    NORTH_EAST(0.5, 0.5),
    SOUTH_WEST(-0.5, -0.5),
    SOUTH_EAST(0.5, -0.5),
    SOUTH(0, -1);

    final double x;
    final double y;

    HexagonDirection(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static HexagonDirection fromStr(String letters) {
        return switch (letters) {
            case "n" -> HexagonDirection.NORTH;
            case "s" -> HexagonDirection.SOUTH;
            case "ne" -> HexagonDirection.NORTH_EAST;
            case "nw" -> HexagonDirection.NORTH_WEST;
            case "se" -> HexagonDirection.SOUTH_EAST;
            case "sw" -> HexagonDirection.SOUTH_WEST;
            default -> throw new IllegalArgumentException(letters + "is not a valid direction.");
        };
    }

    public HexagonPosition move(HexagonPosition from) {
        final double newX = from.x() + x;
        final var newY = from.y() + y;
        return new HexagonPosition(newX, newY);
    }
}
