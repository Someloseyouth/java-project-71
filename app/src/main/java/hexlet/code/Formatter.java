package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public class Formatter {

    public static String format(List<Node> diffTree, String formatName) throws Exception {
        return switch (formatName.toLowerCase()) {
            case "stylish" -> StylishFormatter.format(diffTree);
            case "plain" -> PlainFormatter.format(diffTree);
            case "json" -> JsonFormatter.format(diffTree);
            default -> StylishFormatter.format(diffTree);
        };
    }
}
