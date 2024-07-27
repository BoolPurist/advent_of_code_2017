package cli;

import com.beust.jcommander.Parameter;
import solutions.GivenTask;

public class CliArgs {
    @Parameter(names = { "-d", "--day" }, required = true, validateWith = DayNumber.class)
    private int day;
    @Parameter(names = { "-h", "--help" }, help = true)
    private boolean help;
    @Parameter(names = { "-p", "--path" }, required = false)
    private String path;
    @Parameter(names = { "-e",
            "--expression" }, description = "Takes this argument as the direct input. Has pririoty over argument path.", required = false)
    private String expression;
    @Parameter(names = { "-t",
            "--task" }, required = true, description = "Takes this argument as a path.Loads input from the file at the given path.", validateWith = ValidateGivenTask.class, converter = ConvertGivenTask.class)
    private GivenTask task;

    public String getExpression() {
        return expression;
    }

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
