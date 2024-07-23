package cli;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class DayNumber implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        var number = Integer.parseInt(value);
        if (number < 1 || number > 24) {
            throw new ParameterException(name + " must be between 1 and 24");
        }
    }
}
