
package solutions.day_4;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import solutions.InvalidInputException;
import solutions.ParseInput;
import solutions.ProducesSolution;

public class ParserDayFour implements ParseInput {

	@Override
	public ProducesSolution parse(String input) throws InvalidInputException {
        var data = input.lines()
            .map(line -> Stream.of(line.split("\\s")).collect(Collectors.toList())
                
            ).collect(Collectors.toList());
        return new SolverDayFour(data);
        
	}
    
}
