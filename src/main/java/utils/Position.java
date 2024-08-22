package utils;

public record Position(int x, int y) {
    public static Position clampPositionWithinBounding(GridBoundary boundary, Position position) {
        final var clampedX = Math.clamp(position.x(), boundary.minWidth(), boundary.maxWidth());
        final var clampedY = Math.clamp(position.y(), boundary.minHeight(), boundary.maxHeight());
        return new Position(clampedX, clampedY);
    }

    public static Position zero() {
        return new Position(0, 0);
    }
   

    public Position up() {
        return new Position(this.x, this.y + 1);
    }

    public Position down() {
        return new Position(this.x, this.y - 1);
    }

    public Position left() {
        return new Position(this.x - 1, this.y);
    }

    public Position right() {
        return new Position(this.x + 1, this.y);
    }

    public Position upWithinBounding(GridBoundary boundary) {
        return clampPositionWithinBounding(boundary, up());
    }

    public Position leftWithinBounding(GridBoundary boundary) {
        return clampPositionWithinBounding(boundary, left());
    }

    public Position downWithinBounding(GridBoundary boundary) {
        return clampPositionWithinBounding(boundary, down());
    }

    public Position rightWithinBounding(GridBoundary boundary) {
        return clampPositionWithinBounding(boundary, right());
    }
}

