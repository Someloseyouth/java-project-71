package hexlet.code;

import hexlet.code.formatters.StylishFormatter; // Добавляем импорт

import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);

        List<Node> diffTree = DifferTreeBuilder.build(map1, map2);

        if ("stylish".equalsIgnoreCase(format)) {
            // Вызываем форматер из другого пакета
            return StylishFormatter.format(diffTree);
        } else {
            // Фоллбэк на stylish
            return StylishFormatter.format(diffTree);
        }
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}