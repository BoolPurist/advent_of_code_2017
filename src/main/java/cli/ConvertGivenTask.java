package cli;

import com.beust.jcommander.IStringConverter;
import solutions.GivenTask;

public class ConvertGivenTask implements IStringConverter<GivenTask> {

    @Override
    public GivenTask convert(String s) {
        int numberValue = Integer.parseInt(s);
        return switch (numberValue) {
            case 1 -> GivenTask.First;
            case 2 -> GivenTask.Second;
            default -> throw new RuntimeException("aa");
        };
    }
}
