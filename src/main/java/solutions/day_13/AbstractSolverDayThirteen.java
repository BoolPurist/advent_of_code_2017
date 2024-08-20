package solutions.day_13;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSolverDayThirteen {

    protected final List<FirewallLayerConfiguration> firewalls;

    protected AbstractSolverDayThirteen(List<FirewallLayerConfiguration> firewalls) {
        this.firewalls = firewalls;
    }

    public final Map<Integer, Integer> createMapIndexToHeight() {
        final var sorted = firewalls.stream().sorted(Comparator.comparingInt(FirewallLayerConfiguration::index)).toList();
        final var opt_max = sorted.stream().max(Comparator.comparingInt(FirewallLayerConfiguration::index));
        var notSingleLayers = new HashMap<Integer, Integer>();
        if (opt_max.isEmpty()) {
            return notSingleLayers;
        }
        for (final var nextLayer : sorted) {
            notSingleLayers.put(nextLayer.index(), nextLayer.height());
        }
        return notSingleLayers;
    }

    protected final int findHowManyFirewalls() {
        final var optHowManyFirewalls = firewalls.stream().map(FirewallLayerConfiguration::index).max(Comparator.naturalOrder());
        return optHowManyFirewalls.map(integer -> integer + 1).orElse(0);
    }

}
