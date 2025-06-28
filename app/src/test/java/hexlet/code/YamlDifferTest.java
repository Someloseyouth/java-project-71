package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YamlDifferTest {
    @Test
    void testYamlGenerateDiff() throws Exception {
        // Пути к файлам
        Path file1 = Path.of("src/test/resources/fixtures/yaml1.yaml");
        Path file2 = Path.of("src/test/resources/fixtures/yaml2.yaml");
        Path expectedFile = Path.of("src/test/resources/fixtures/expectedYaml.txt");

        // Читаем ожидаемый результат
        String expected = Files.readString(expectedFile).trim();

        // Получаем результат работы программы
        String actual = Differ.generate(file1.toString(), file2.toString()).trim();

        // Проверяем
        assertEquals(expected.trim().replaceAll("\\R", "\n"),
                actual.trim().replaceAll("\\R", "\n"));
    }

    @Test
    void testVoidYamlGenerateDiff() throws Exception {
        String yaml1 = "";
        String yaml2 = "";
        String expected = "{\n}";

        Path file1 = Files.createTempFile("test1", ".yaml");
        Path file2 = Files.createTempFile("test2", ".yaml");

        Files.writeString(file1, yaml1);
        Files.writeString(file2, yaml2);

        String actual = Differ.generate(file1.toString(), file2.toString()).trim();
        assertEquals(expected, actual);
    }

    @Test
    void testPositiveYamlGenerateDiff() throws Exception {
        String yaml1 = "";
        String yaml2 = "key: value";
        String expected = "{\n  + key: value\n}";

        Path file1 = Files.createTempFile("test1", ".yaml");
        Path file2 = Files.createTempFile("test2", ".yaml");

        Files.writeString(file1, yaml1);
        Files.writeString(file2, yaml2);

        String actual = Differ.generate(file1.toString(), file2.toString()).trim();
        assertEquals(expected.trim().replaceAll("\\R", "\n"), actual.trim().replaceAll("\\R", "\n"));
    }

    @Test
    void testNegativeYamlGenerateDiff() throws Exception {
        String yaml1 = "key: value";
        String yaml2 = "";
        String expected = "{\n  - key: value\n}";

        Path file1 = Files.createTempFile("test1", ".yaml");
        Path file2 = Files.createTempFile("test2", ".yaml");

        Files.writeString(file1, yaml1);
        Files.writeString(file2, yaml2);

        String actual = Differ.generate(file1.toString(), file2.toString()).trim();
        assertEquals(expected.trim().replaceAll("\\R", "\n"), actual.trim().replaceAll("\\R", "\n"));
    }

    @Test
    void testNullKeyYamlGenerateDiff() throws Exception {
        String yaml1 = "key: null";
        String yaml2 = "";
        String expected = "{\n  - key: null\n}";

        Path file1 = Files.createTempFile("test1", ".yaml");
        Path file2 = Files.createTempFile("test2", ".yaml");

        Files.writeString(file1, yaml1);
        Files.writeString(file2, yaml2);

        String actual = Differ.generate(file1.toString(), file2.toString()).trim();
        assertEquals(expected.trim().replaceAll("\\R", "\n"), actual.trim().replaceAll("\\R", "\n"));
    }
}
