package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class JsonFormatterTest {

    @Test
    void testFormatSimpleNodes() throws Exception {
        List<Node> nodes = List.of(
                new Node("key1", Node.Status.ADDED, null, "newValue"),
                new Node("key2", Node.Status.REMOVED, "oldValue", null),
                new Node("key3", Node.Status.UNCHANGED, "same", "same"),
                new Node("key4", Node.Status.CHANGED, "old", "new")
        );

        String json = JsonFormatter.format(nodes);

        assertNotNull(json);
        assertTrue(json.contains("\"key\":\"key1\""));
        assertTrue(json.contains("\"status\":\"added\""));
        assertTrue(json.contains("\"valueAfter\":\"newValue\""));
        assertTrue(json.contains("\"valueBefore\":null"));

        assertTrue(json.contains("\"key\":\"key2\""));
        assertTrue(json.contains("\"status\":\"removed\""));
        assertTrue(json.contains("\"valueBefore\":\"oldValue\""));
        assertTrue(json.contains("\"valueAfter\":null"));

        assertTrue(json.contains("\"key\":\"key3\""));
        assertTrue(json.contains("\"status\":\"unchanged\""));
        assertTrue(json.contains("\"valueBefore\":\"same\""));
        assertTrue(json.contains("\"valueAfter\":\"same\""));

        assertTrue(json.contains("\"key\":\"key4\""));
        assertTrue(json.contains("\"status\":\"changed\""));
        assertTrue(json.contains("\"valueBefore\":\"old\""));
        assertTrue(json.contains("\"valueAfter\":\"new\""));
    }

    @Test
    void testFormatNestedNodes() throws Exception {
        List<Node> childNodes = List.of(
                new Node("childKey", Node.Status.ADDED, null, "childValue")
        );
        Node parentNode = new Node("parentKey", Node.Status.NESTED, childNodes);

        String json = JsonFormatter.format(List.of(parentNode));

        assertNotNull(json);
        assertTrue(json.contains("\"key\":\"parentKey\""));
        assertTrue(json.contains("\"status\":\"nested\""));
        assertTrue(json.contains("\"children\""));
        assertTrue(json.contains("\"key\":\"childKey\""));
        assertTrue(json.contains("\"valueAfter\":\"childValue\""));
    }

    @Test
    void testFormatEmptyList() throws Exception {
        String json = JsonFormatter.format(List.of());
        assertEquals("[]", json);
    }
}
