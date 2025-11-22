package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonFormatterTest {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testFormatEmptyList() throws Exception {
        List<Node> emptyList = List.of();
        String result = JsonFormatter.format(emptyList);
        assertEquals("[]", result);
    }

    @Test
    void testFormatSingleNode() throws Exception {
        Node node = new Node("key1", Node.Status.ADDED, null, "value");
        List<Node> diff = List.of(node);

        String actualJson = JsonFormatter.format(diff);

        String expectedJson = "["
                + "{"
                + "\"key\":\"key1\","
                + "\"status\":\"added\","
                + "\"valueBefore\":null,"
                + "\"valueAfter\":\"value\","
                + "\"children\":null"
                + "}"
                + "]";

        JsonNode actualTree = mapper.readTree(actualJson);
        JsonNode expectedTree = mapper.readTree(expectedJson);

        assertEquals(expectedTree, actualTree);
    }
}
