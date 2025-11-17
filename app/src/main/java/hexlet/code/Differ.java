package hexlet.code;

import java.util.List;
import java.util.Map;

public class Differ {

    // Приватный конструктор для предотвращения создания экземпляров
    private Differ() {

    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);

        List<Node> diffTree = DifferTreeBuilder.build(map1, map2);

        return Formatter.format(diffTree, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}