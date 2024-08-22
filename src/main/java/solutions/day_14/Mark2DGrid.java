package solutions.day_14;

import solutions.day_10.KnotHashing;
import utils.Position;

import java.util.List;

record Mark2DGrid(List<List<Boolean>> rawData) {

    public static Mark2DGrid fromKnotHashes(List<String> knotHashes) {
        final var grid = knotHashes.stream()
                .map(KnotHashing::createKnotHashFrom)
                .map(SolverDayPart2Fourteen::turnTextIntoBoolRow)
                .toList();
        return new Mark2DGrid(grid);
    }

    public boolean accessGridCell(Position cell) {
        return rawData.get(cell.x()).get(cell.y());
    }
}
