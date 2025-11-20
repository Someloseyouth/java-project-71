package hexlet.code;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    void testRunWithDefaultFormat() {
        String[] args = {"src/test/resources/fixtures/filepath1.json", "src/test/resources/fixtures/filepath2.json"};
        int exitCode = new CommandLine(new App()).execute(args);
        assertEquals(0, exitCode);
    }

    @Test
    void testRunWithFormat() {
        String[] args = {"src/test/resources/fixtures/filepath1.json",
            "src/test/resources/fixtures/filepath2.json", "-f", "plain"};
        int exitCode = new CommandLine(new App()).execute(args);
        assertEquals(0, exitCode);
    }
}
