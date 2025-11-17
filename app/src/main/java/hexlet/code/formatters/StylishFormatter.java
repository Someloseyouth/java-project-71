package hexlet.code.formatters;

import hexlet.code.Node;

import java.util.List;

public class StylishFormatter {

    public static String format(List<Node> diffTree) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        buildFormattedString(diffTree, result, 1); // Начинаем с отступа 1
        result.append("}");
        return result.toString();
    }

    private static void buildFormattedString(List<Node> nodes, StringBuilder result, int depth) {
        String indent = "  ".repeat(depth); // 2 пробела на уровень

        for (Node node : nodes) {
            String key = node.getKey();
            Node.Status status = node.getStatus();

            switch (status) {
                case Node.Status.ADDED:
                    result.append(indent)
                            .append("+ ")
                            .append(key)
                            .append(": ")
                            .append(formatValue(node.getValueAfter()))
                            .append("\n");
                    break;
                case Node.Status.REMOVED:
                    result.append(indent)
                            .append("- ")
                            .append(key)
                            .append(": ")
                            .append(formatValue(node.getValueBefore()))
                            .append("\n");
                    break;
                case Node.Status.UNCHANGED:
                    result.append(indent)
                            .append("  ")
                            .append(key)
                            .append(": ")
                            .append(formatValue(node.getValueAfter()))
                            .append("\n");
                    break;
                case Node.Status.CHANGED:
                    result.append(indent)
                            .append("- ")
                            .append(key)
                            .append(": ")
                            .append(formatValue(node.getValueBefore()))
                            .append("\n");
                    result.append(indent)
                            .append("+ ")
                            .append(key)
                            .append(": ")
                            .append(formatValue(node.getValueAfter()))
                            .append("\n");
                    break;
                case Node.Status.NESTED:
                    result.append(indent)
                            .append("  ")
                            .append(key)
                            .append(": ");
                    result.append("{\n");
                    buildFormattedString(node.getChildren(), result, depth + 1);
                    result.append("  ".repeat(depth)).append("}\n");
                    break;
                default:
                    // не должен срабатывать
                    break;
            }
        }
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else {
            return value.toString();
        }
    }
}
