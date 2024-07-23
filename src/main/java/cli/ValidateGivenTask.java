package cli;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateGivenTask implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        var numberValue = Integer.parseInt(value);
        if (numberValue < 1 || numberValue > 2) {
            throw new ParameterException("Number must be between 1 and 2");
        }
    }
}
