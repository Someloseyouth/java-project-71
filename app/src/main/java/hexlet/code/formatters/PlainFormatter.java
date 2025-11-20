package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.List;

public class PlainFormatter {

    public static String format(List<Node> diffTree) {
        StringBuilder result = new StringBuilder();
        buildFormattedString(diffTree, result, "");
        // Убираем последний символ новой строки, если он есть
        if (result.length() > 0 && result.charAt(result.length() - 1) == '\n') {
            result.setLength(result.length() - 1);
        }
        return result.toString();
    }

    private static void buildFormattedString(List<Node> nodes, StringBuilder result, String parentPath) {
        for (Node node : nodes) {
            String currentKey = node.getKey();
            String fullPath = parentPath.isEmpty() ? currentKey : parentPath + "." + currentKey;
            Node.Status status = node.getStatus();

            switch (status) {
                case Node.Status.ADDED:
                    result.append(String.format("Property '%s' was added with value: %s%n",
                            fullPath, formatValue(node.getValueAfter())));
                    break;
                case Node.Status.REMOVED:
                    result.append(String.format("Property '%s' was removed%n", fullPath));
                    break;
                case Node.Status.CHANGED:
                    result.append(String.format("Property '%s' was updated. From %s to %s%n",
                            fullPath, formatValue(node.getValueBefore()), formatValue(node.getValueAfter())));
                    break;
                case Node.Status.UNCHANGED:
                    break;
                case Node.Status.NESTED:
                    buildFormattedString(node.getChildren(), result, fullPath);
                    break;
                default:
                    throw new RuntimeException("Unknown type: '" + status + "'");
            }
        }
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof List || value instanceof java.util.Map) {
            // Для составных значений (объектов, массивов) выводим [complex value]
            return "[complex value]";
        } else {
            return value.toString();
        }
    }
}
