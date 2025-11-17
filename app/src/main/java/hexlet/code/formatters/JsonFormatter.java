package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hexlet.code.Node;

import java.util.List;

public class JsonFormatter {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static String format(List<Node> diffTree) throws Exception {
        ArrayNode resultArray = objectMapper.createArrayNode();

        buildJsonArray(diffTree, resultArray);

        return objectMapper.writeValueAsString(resultArray);
    }

    private static void buildJsonArray(List<Node> nodes, ArrayNode targetArray) {
        for (Node node : nodes) {
            ObjectNode nodeObject = objectMapper.createObjectNode();
            nodeObject.put("key", node.getKey());
            nodeObject.put("status", node.getStatus().toString().toLowerCase());

            nodeObject.set("valueBefore", node.getValueBefore() != null ? objectMapper.valueToTree(node.getValueBefore()) : null);
            nodeObject.set("valueAfter", node.getValueAfter() != null ? objectMapper.valueToTree(node.getValueAfter()) : null);

            if (node.getChildren() != null) {
                ArrayNode childrenArray = objectMapper.createArrayNode();
                buildJsonArray(node.getChildren(), childrenArray);
                nodeObject.set("children", childrenArray);
            } else {
                nodeObject.set("children", (ArrayNode) null);
            }

            targetArray.add(nodeObject);
        }
    }
}
