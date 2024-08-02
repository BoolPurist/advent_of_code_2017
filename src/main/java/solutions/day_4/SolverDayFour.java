
package solutions.day_4;

import java.util.HashSet;
import java.util.List;

import solutions.GivenTask;
import solutions.ProducesSolution;


public class SolverDayFour implements ProducesSolution {
    
    private final List<List<String>> data;
    
    public SolverDayFour(List<List<String>> data) {
        this.data = data;
    }
    
	@Override
	public String produce(GivenTask task) {
        var alreadyFound = new HashSet<>();
        var sum = 0;
        for (var nextLine  : this.data) {
            var toAdd = 1;
            for (var nextSequence : nextLine) {
                if (alreadyFound.contains(nextSequence)) {
                    toAdd = 0;
                    break; 
                } else {
                    alreadyFound.add(nextSequence);
                }
            }
            sum += toAdd;
            alreadyFound.clear();
        }
        return String.valueOf(sum);
	}
}
