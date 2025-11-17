package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "Path to first file")
    private String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "Path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception {
        String diff = Differ.generate(filepath1, filepath2, format);
        System.out.println(diff);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
