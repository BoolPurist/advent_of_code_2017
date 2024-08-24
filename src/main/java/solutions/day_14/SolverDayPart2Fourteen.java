package solutions.day_14;

import solutions.ProvidesPuzzleSolution;
import utils.GridBoundary;
import utils.Position;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolverDayPart2Fourteen extends AbstractSolverDayFourteen implements ProvidesPuzzleSolution {
    private static final GridBoundary BOUNDARY = new GridBoundary(0, 127, 0, 127);

    protected SolverDayPart2Fourteen(String unPrefixedHash) {
        super(unPrefixedHash);
    }


    private static Position[] getNextPositions(Position nextInRegion) {
        return new Position[]{
                nextInRegion.upWithinBounding(BOUNDARY),
                nextInRegion.downWithinBounding(BOUNDARY),
                nextInRegion.leftWithinBounding(BOUNDARY),
                nextInRegion.rightWithinBounding(BOUNDARY)};
    }

    private static int traverseAllRegionsIn(Mark2DGrid grid) {
        var searchingMarkedInRegion = grid.accessGridCell(Position.zero());
        var foundRegions = searchingMarkedInRegion ? 1 : 0;
        var context = SearchRegionContext.init(searchingMarkedInRegion, grid);

        while (true) {
            if (searchingMarkedInRegion) {
                final var nextInRegion = context.regionPool.poll();
                if (nextInRegion == null) {
                    searchingMarkedInRegion = false;
                } else {
                    lookAtNeighboursForInRegion(nextInRegion, context);
                }
            } else {
                final var nextUnmarked = context.notMarked.poll();
                if (nextUnmarked == null) {
                    return foundRegions;
                }
                if (lookAtNeighboursForUnmarked(nextUnmarked, context)) {
                    foundRegions++;
                    searchingMarkedInRegion = true;
                }
            }
        }
    }

    private static void lookAtNeighboursForInRegion(Position nextUnmarked, SearchRegionContext context
    ) {
        var nextPositions = getNextPositions(nextUnmarked);
        for (final var nextPosition : nextPositions) {
            if (context.alreadyVisited.add(nextPosition)) {
                final var isMarked = context.grid.accessGridCell(nextPosition);
                if (isMarked) {
                    context.regionPool.push(nextPosition);
                } else {
                    context.notMarked.add(nextPosition);
                }
            }

        }
    }

    private static boolean lookAtNeighboursForUnmarked(Position nextUnmarked, SearchRegionContext context
    ) {
        var nextPositions = getNextPositions(nextUnmarked);
        for (final var nextPosition : nextPositions) {
            if (context.alreadyVisited.add(nextPosition)) {
                final var isMarked = context.grid.accessGridCell(nextPosition);
                if (isMarked) {
                    context.regionPool.push(nextPosition);
                    context.notMarked.push(nextUnmarked);
                    return true;
                } else {
                    context.notMarked.add(nextPosition);
                }
            }
        }
        return false;
    }


    @Override
    public String produce() {
        final var allLines = SolverDayFourteen.createNumberedPrefixedWith(unPrefixedHash, PREFIX, HOW_MANY_TIMES);
        final var marked2DGrid = Mark2DGrid.fromKnotHashes(allLines);
        final var regionCount = traverseAllRegionsIn(marked2DGrid);
        return String.valueOf(regionCount);
    }

    private record SearchRegionContext(
            Mark2DGrid grid,
            Set<Position> alreadyVisited,
            ArrayDeque<Position> regionPool,
            ArrayDeque<Position> notMarked
    ) {

        public static SearchRegionContext init(boolean searchingMarkedInRegion, Mark2DGrid grid) {
            ArrayDeque<Position> notMarked = searchingMarkedInRegion ? new ArrayDeque<>() : new ArrayDeque<>(List.of(Position.zero()));
            ArrayDeque<Position> regionPool = searchingMarkedInRegion ? new ArrayDeque<>(List.of(Position.zero())) : new ArrayDeque<>();
            HashSet<Position> alreadyVisited = new HashSet<>();
            alreadyVisited.add(new Position(0, 0));
            return new SearchRegionContext(grid, alreadyVisited, regionPool, notMarked);
        }

    }
}
