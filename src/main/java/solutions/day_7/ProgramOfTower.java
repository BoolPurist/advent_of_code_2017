package solutions.day_7;

import java.util.List;

public record ProgramOfTower(String name, int weight, List<String> children) {
    public ProgramOfTower(String name, int weight, List<String> children) {
        this.name = name;
        this.weight = weight;
        this.children = children.stream().toList();
    }
}
