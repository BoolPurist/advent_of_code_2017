
package solutions.day_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import solutions.GivenTask;
import solutions.ProducesSolution;

public class SolverDayFour implements ProducesSolution {

    private static final int IS_ANAGRAM = 0;
    private static final int IS_NO_ANAGRAM = 1;

    private final List<List<String>> data;

    public SolverDayFour(List<List<String>> data) {
        this.data = data;
    }

    @Override
    public String produce(GivenTask task) {
        switch (task) {
            case GivenTask.First:
                return this.solveFirstTask();
            default:
                return this.solveSecondTask();
        }
    }

    private String solveFirstTask() {
        var alreadyFound = new HashSet<>();
        var sum = 0;
        for (var nextLine : this.data) {
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

    private String solveSecondTask() {
        var alreadyFound = new ArrayList<HashMap<Character, Integer>>();
        var sum = 0;
        for (var nextLine : this.data) {
            var toAdd = IS_NO_ANAGRAM;
            line: for (var nextSequence : nextLine) {
                var characterMap = new HashMap<Character, Integer>();
                for (int index = 0; index < nextSequence.length(); index++) {
                    var nextChar = nextSequence.charAt(index);
                    if (characterMap.containsKey(nextChar)) {
                        var _ = characterMap.computeIfPresent(nextChar, (_, v) -> v + 1);
                    } else {
                        characterMap.put(nextChar, 1);
                    }
                }
                if (isAnagram(alreadyFound, characterMap)) {
                    toAdd = IS_ANAGRAM;
                    break line;
                }
                alreadyFound.add(characterMap);
            }

            sum += toAdd;
            alreadyFound.clear();
        }
        return String.valueOf(sum);
    }

    private static boolean isAnagram(List<HashMap<Character, Integer>> possibleAnagrams,
            HashMap<Character, Integer> inspectedSequence) {
        if (possibleAnagrams.isEmpty()) {
            return false;
        }

        final var inspectedSize = inspectedSequence.size();

        for (var nextPrevious : possibleAnagrams) {
            var isAnagram = true;
            final var previousHowMany = nextPrevious.size();
            final var notSameSize = previousHowMany != inspectedSize;
            if (notSameSize) {
                continue;
            }
            for (var nextCharAndItsCount : nextPrevious.entrySet()) {
                final var currentInspectedChar = nextCharAndItsCount.getKey();
                final var howManyTimes = nextCharAndItsCount.getValue();
                final var countOfInspected = inspectedSequence.get(currentInspectedChar);
                final var notWithinInspected = countOfInspected == null;
                final var differentCharCount = howManyTimes != countOfInspected;

                if (notWithinInspected || differentCharCount) {
                    isAnagram = false;
                    break;
                }
            }
            if (isAnagram) {
                return true;
            }
        }
        return false;
    }

}
