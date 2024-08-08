package solutions.day_7;

import java.util.Objects;
import java.util.Optional;

public record TowerProgramInHierarchy(ProgramOfTower program, Optional<TowerProgramInHierarchy> parent) {
    public TowerProgramInHierarchy {
        Objects.requireNonNull(parent);
    }
}
