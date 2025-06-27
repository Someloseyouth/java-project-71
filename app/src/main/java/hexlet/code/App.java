package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.Callable;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "Path to first file")
    private String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "Path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: ${DEFAULT-VALUE}]")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> data1 = objectMapper.readValue(Files.readString(Paths.get(filepath1)), Map.class);
        Map<String, Object> data2 = objectMapper.readValue(Files.readString(Paths.get(filepath2)), Map.class);

        System.out.println("File 1: " + data1);
        System.out.println("File 2: " + data2);

        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}