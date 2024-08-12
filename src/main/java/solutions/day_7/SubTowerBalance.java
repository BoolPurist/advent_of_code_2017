package solutions.day_7;

import utils.PositiveInteger;

import java.util.Objects;
import java.util.Optional;

public record SubTowerBalance(TowerProgramInHierarchy node, PositiveInteger totalWeight,
                              Optional<PositiveInteger> supposedWeight) {
    public SubTowerBalance {
        Objects.requireNonNull(supposedWeight);
    }

    public static SubTowerBalance withoutHoldingDisc(TowerProgramInHierarchy root) {
        return new SubTowerBalance(root, new PositiveInteger(root.program().weight()), Optional.empty());
    }

    public PositiveInteger weight() {
        return new PositiveInteger(this.node.program().weight());
    }

    public SubTowerBalance isUnbalanced(PositiveInteger supposedWeight) {
        Objects.requireNonNull(supposedWeight);
        return new SubTowerBalance(this.node, this.totalWeight, Optional.of(supposedWeight));
    }

    public SubTowerBalance addChild(SubTowerBalance child) {
        final var totalWeight = child.totalWeight().add(this.totalWeight);
        return new SubTowerBalance(this.node, totalWeight, this.supposedWeight);
    }
}
