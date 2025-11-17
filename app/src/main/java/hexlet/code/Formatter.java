package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public class Formatter {

    public static String format(List<Node> diffTree, String formatName) {
        return switch (formatName.toLowerCase()) {
            case "stylish" -> StylishFormatter.format(diffTree);
            case "plain" -> PlainFormatter.format(diffTree);
            // Можно добавить другие форматы сюда
            default -> StylishFormatter.format(diffTree);
        };
    }
}