package cli;

import com.beust.jcommander.Parameter;
import solutions.GivenTask;

public class CliArgs {
    @Parameter(names = {"-d", "--day"}, required = true, validateWith = DayNumber.class)
    private int day;
    @Parameter(names = {"-h", "--help"}, help = true)
    private boolean help;
    @Parameter(names = {"-p", "--path"}, required = true)
    private String path;

    @Parameter(names = {"-t", "--task"}, required = true, validateWith = ValidateGivenTask.class, converter = ConvertGivenTask.class)
    private GivenTask task;

    public boolean isHelp() {
        return help;
    }

    public int getDay() {
        return day;
    }

    public String getPath() {
        return path;
    }

    public GivenTask getTask() {
        return task;
    }
}
