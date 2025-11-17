package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hexlet.code.Node;

import java.util.List;

public class JsonFormatter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static String format(List<Node> diffTree) throws Exception {
        ArrayNode resultArray = OBJECT_MAPPER.createArrayNode();

        buildJsonArray(diffTree, resultArray);

        return OBJECT_MAPPER.writeValueAsString(resultArray);
    }

    private static void buildJsonArray(List<Node> nodes, ArrayNode targetArray) {
        for (Node node : nodes) {
            ObjectNode nodeObject = OBJECT_MAPPER.createObjectNode();
            nodeObject.put("key", node.getKey());
            nodeObject.put("status", node.getStatus().toString().toLowerCase());

            nodeObject.set("valueBefore", node.getValueBefore() != null
                    ? OBJECT_MAPPER.valueToTree(node.getValueBefore()) : null);
            nodeObject.set("valueAfter", node.getValueAfter() != null
                    ? OBJECT_MAPPER.valueToTree(node.getValueAfter()) : null);

            if (node.getChildren() != null) {
                ArrayNode childrenArray = OBJECT_MAPPER.createArrayNode();
                buildJsonArray(node.getChildren(), childrenArray);
                nodeObject.set("children", childrenArray);
            } else {
                nodeObject.set("children", (ArrayNode) null);
            }

            targetArray.add(nodeObject);
        }
    }
}
