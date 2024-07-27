package solutions.day_3;

public record Position(int x, int y) {
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
}
