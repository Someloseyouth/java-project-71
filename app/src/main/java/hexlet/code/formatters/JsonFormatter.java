package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hexlet.code.Node;

import java.util.List;

public class JsonFormatter {

    public static String format(List<Node> diff) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diff);
    }

    private static void buildJsonArray(ObjectMapper mapper, List<Node> nodes, ArrayNode targetArray) {
        for (Node node : nodes) {
            ObjectNode nodeObject = mapper.createObjectNode();
            nodeObject.put("key", node.getKey());
            nodeObject.put("status", node.getStatus().toString().toLowerCase());

            if (node.getValueBefore() != null) {
                nodeObject.set("valueBefore", mapper.valueToTree(node.getValueBefore()));
            } else {
                nodeObject.putNull("valueBefore");
            }

            if (node.getValueAfter() != null) {
                nodeObject.set("valueAfter", mapper.valueToTree(node.getValueAfter()));
            } else {
                nodeObject.putNull("valueAfter");
            }

            if (node.getChildren() != null && !node.getChildren().isEmpty()) {
                ArrayNode childrenArray = mapper.createArrayNode();
                buildJsonArray(mapper, node.getChildren(), childrenArray);
                nodeObject.set("children", childrenArray);
            } else {
                nodeObject.putNull("children");
            }

            targetArray.add(nodeObject);
        }
    }
}
