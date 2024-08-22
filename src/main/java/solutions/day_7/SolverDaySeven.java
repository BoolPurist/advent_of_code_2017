package solutions.day_7;

import solutions.GivenTask;
import solutions.ProvidesPuzzleSolution;
import utils.Pair;
import utils.PositiveInteger;
import utils.ReadOnlyList;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SolverDaySeven implements ProvidesPuzzleSolution {
    private final ReadOnlyList<ProgramOfTower> data;
    private GivenTask task;

    public SolverDaySeven(ReadOnlyList<ProgramOfTower> input, GivenTask task) {
        this.data = input;
        this.task = task;
    }

    @Override
    public String produce() {
        return switch (task) {
            case FIRST -> buildUpTowerOfPrograms().first();
            case SECOND -> String.valueOf(calcCorrectionForTheOneUnbalanced().value());
        };
    }

    private PositiveInteger calcCorrectionForTheOneUnbalanced() {
        final var rootAndTower = buildUpTowerOfPrograms();
        final var root = rootAndTower.first();
        final var tree = rootAndTower.second();
        final var currentRoot = tree.get(root);
        assert currentRoot != null;


        final var result = searchForUnbalanced(
                new SubTowerBalance(
                        currentRoot, PositiveInteger.zero(), Optional.empty()
                ),
                tree
        );
        final var supposedWeight = result.supposedWeight();
        assert supposedWeight.isPresent() : "No unbalanced weight found";
        return supposedWeight.get();
    }

    private SubTowerBalance searchForUnbalanced(final SubTowerBalance currentRoot, final Map<String, TowerProgramInHierarchy> tree) {
        final var children = currentRoot.node().program().children();
        if (children.isEmpty()) {
            return currentRoot;
        }

        final var first = children.getFirst();
        final var firstChild = searchForUnbalanced(SubTowerBalance.withoutHoldingDisc(tree.get(first)), tree);
        if (firstChild.supposedWeight().isPresent()) {
            return firstChild;
        }
        final var sameWeight = firstChild.totalWeight();
        var sum = sameWeight;
        final var afterFirst = children.stream().skip(1);
        for (final var child : afterFirst.toList()) {
            final var node = tree.get(child);
            final var summedChild = searchForUnbalanced(SubTowerBalance.withoutHoldingDisc(node), tree);
            if (summedChild.supposedWeight().isPresent()) {
                return summedChild;
            }
            final var currentWeight = summedChild.totalWeight();
            sum = sum.add(currentWeight);
            final var signedSupposedWeight = sameWeight.value();
            final var signedCurrentWeight = currentWeight.value();
            if (signedCurrentWeight > signedSupposedWeight) {
                final var diff = signedCurrentWeight - signedSupposedWeight;
                final var supposedWeight = summedChild.weight().value() - diff;
                return new SubTowerBalance(summedChild.node(), sum, Optional.of(new PositiveInteger(supposedWeight)));
            } else if (signedCurrentWeight < signedSupposedWeight) {
                final var diff = signedSupposedWeight - signedCurrentWeight;
                final var supposedWeight = firstChild.weight().value() - diff;
                return new SubTowerBalance(summedChild.node(), sum, Optional.of(new PositiveInteger(supposedWeight)));
            }
        }
        sum = currentRoot.totalWeight().add(sum);
        return new SubTowerBalance(currentRoot.node(), sum, Optional.empty());

    }

    private Pair<String, Map<String, TowerProgramInHierarchy>> buildUpTowerOfPrograms() {

        final var nameMappedToProgram = this.data.list().stream().collect(Collectors.toMap(ProgramOfTower::name, program -> program));
        HashMap<String, TowerProgramInHierarchy> towerOfPrograms = new HashMap<>();
        for (final var nextProgram : this.data.list()) {

            final var name = nextProgram.name();

            towerOfPrograms.computeIfAbsent(name, _ -> new TowerProgramInHierarchy(nextProgram, Optional.empty()));

            final var parent = towerOfPrograms.get(name);
            assert parent != null;
            for (final var childName : nextProgram.children()) {
                final var childWithoutHierarchy = nameMappedToProgram.get(childName);
                assert childWithoutHierarchy != null;
                final var childInHierarchy = new TowerProgramInHierarchy(childWithoutHierarchy, Optional.of(parent));
                towerOfPrograms.put(childName, childInHierarchy);
            }

        }
        assert towerOfPrograms.size() == nameMappedToProgram.size();
        final var shouldBeOneElement = towerOfPrograms.entrySet().stream().filter(next -> next.getValue().parent().isEmpty()).toList();
        assert shouldBeOneElement.size() == 1;
        final var name = shouldBeOneElement.getFirst().getKey();
        return new Pair<>(name, towerOfPrograms);
    }
}
