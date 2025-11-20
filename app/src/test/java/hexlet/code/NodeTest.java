package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NodeTest {

    @Test
    void testNodeGetters() {
        Node node = new Node("key", Node.Status.ADDED, "valueBefore", "valueAfter");
        assertEquals("key", node.getKey());
        assertEquals(Node.Status.ADDED, node.getStatus());
        assertEquals("valueBefore", node.getValueBefore());
        assertEquals("valueAfter", node.getValueAfter());
        assertNull(node.getChildren());
    }

    @Test
    void testNestedNode() {
        Node child = new Node("childKey", Node.Status.CHANGED, "oldVal", "newVal");
        Node parent = new Node("parentKey", Node.Status.NESTED, List.of(child));
        assertEquals("parentKey", parent.getKey());
        assertEquals(Node.Status.NESTED, parent.getStatus());
        assertNull(parent.getValueBefore());
        assertNull(parent.getValueAfter());
        assertNotNull(parent.getChildren());
        assertEquals(1, parent.getChildren().size());
        assertEquals(child, parent.getChildren().get(0));
    }

    @Test
    void testEqualsAndHashCode() {
        Node node1 = new Node("key", Node.Status.REMOVED, "a", null);
        Node node2 = new Node("key", Node.Status.REMOVED, "a", null);
        Node node3 = new Node("key", Node.Status.ADDED, null, "a");

        assertEquals(node1, node2);
        assertEquals(node1.hashCode(), node2.hashCode());
        assertNotEquals(node1, node3);
    }

    @Test
    void testToStringContainsKeyAndStatus() {
        Node node = new Node("key", Node.Status.CHANGED, "v1", "v2");
        String s = node.toString();
        assertTrue(s.contains("key='key'"));
        assertTrue(s.contains("status=CHANGED"));
    }
}
