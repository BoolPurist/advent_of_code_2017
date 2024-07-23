package cli;

import com.beust.jcommander.JCommander;


public record ParsedCliArgs(CliArgs args, JCommander commander) {
}
