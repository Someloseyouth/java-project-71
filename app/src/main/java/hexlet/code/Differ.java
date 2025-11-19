package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public final class Differ {

    // Приватный конструктор для предотвращения создания экземпляров
    private Differ() {

    }

    private static String readContent(String filepath) throws IOException {
        return Files.readString(Path.of(filepath));
    }

    private static String getFileExtension(String filepath) {
        int lastDotIndex = filepath.lastIndexOf('.');
        if (lastDotIndex == -1) {
            throw new IllegalArgumentException("File does not have an extension: " + filepath);
        }
        return filepath.substring(lastDotIndex + 1).toLowerCase();
    }

    public static String generate(String filepath1, String filepath2, String outputFormat) throws Exception {
        String inputFormat1 = getFileExtension(filepath1);
        String inputFormat2 = getFileExtension(filepath2);

        // Проверка, что форматы входных файлов совпадают
        if (!inputFormat1.equals(inputFormat2)) {
            throw new IllegalArgumentException("Input file formats do not match");
        }
        // Разрешены только json или yaml
        if (!inputFormat1.equals("json") && !inputFormat1.equals("yaml") && !inputFormat1.equals("yml")) {
            throw new IllegalArgumentException("Unsupported input file format: " + inputFormat1);
        }

        String content1 = readContent(filepath1);
        String content2 = readContent(filepath2);

        Map<String, Object> map1 = Parser.parse(content1, inputFormat1);
        Map<String, Object> map2 = Parser.parse(content2, inputFormat2);

        List<Node> diffTree = DifferTreeBuilder.build(map1, map2);

        return Formatter.format(diffTree, outputFormat);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
}
