package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    void testEmptyJsonInput() throws IOException {
        Map<String, Object> result = Parser.parse("", "json");
        assertTrue(result.isEmpty());
    }

    @Test
    void testEmptyYamlInput() throws IOException {
        Map<String, Object> result = Parser.parse("", "yaml");
        assertTrue(result.isEmpty());
    }
}
