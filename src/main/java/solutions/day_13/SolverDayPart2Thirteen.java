package solutions.day_13;

import solutions.ProducesSolution;

import java.util.List;
import java.util.Map;

public final class SolverDayPart2Thirteen extends AbstractSolverDayThirteen implements ProducesSolution {


    public SolverDayPart2Thirteen(List<FirewallLayerConfiguration> firewalls) {
        super(firewalls);
    }

    private static boolean doesNotGetCaughtWithThisDelay(Map<Integer, Integer> mapping, int initialDelay, int howManyFirewalls) {
        var neverCaught = true;

        for (int indexCurrentFirewall = 0, passedPicoSeconds = initialDelay
             ; indexCurrentFirewall < howManyFirewalls
                ; indexCurrentFirewall++, passedPicoSeconds++) {

            final var height = mapping.get(indexCurrentFirewall);

            if (height != null && !height.equals(1)) {
                final var indexOfScanner = SolverDayThirteen.fetchPositionInFirewallLayer(passedPicoSeconds, height);

                final var gotCaught = indexOfScanner.equals(0);
                if (gotCaught) {
                    neverCaught = false;
                }
            }
        }

        return neverCaught;
    }

    private int findFirstDelayWithoutBeingCaught(Map<Integer, Integer> mapping) {
        final var howManyFirewalls = findHowManyFirewalls();
        if (howManyFirewalls == 0) {
            return 0;
        }

        for (int initialDelay = 0; initialDelay < Integer.MAX_VALUE; initialDelay++) {
            final var notCaught = doesNotGetCaughtWithThisDelay(mapping, initialDelay, howManyFirewalls);
            if (notCaught) return initialDelay;
        }

        return Integer.MAX_VALUE;
    }

    @Override
    public String produce() {
        final var indexToHeight = createMapIndexToHeight();

        final var delayWithoutBeingCaught = findFirstDelayWithoutBeingCaught(indexToHeight);
       
        return String.valueOf(delayWithoutBeingCaught);
    }

}
