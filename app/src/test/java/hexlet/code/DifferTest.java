package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    void testGenerateDiff() throws Exception {
        // Пути к файлам
        Path file1 = Path.of("src/test/resources/fixtures/filepath1.json");
        Path file2 = Path.of("src/test/resources/fixtures/filepath2.json");
        Path expectedFile = Path.of("src/test/resources/fixtures/expected.txt");

        // Читаем ожидаемый результат
        String expected = Files.readString(expectedFile).trim();

        // Получаем результат работы программы
        String actual = Differ.generate(file1.toString(), file2.toString()).trim();

        // Проверяем
        assertEquals(expected.trim().replaceAll("\\R", "\n"),
                actual.trim().replaceAll("\\R", "\n"));
    }

    @Test
    void testVoidGenerateDiff() throws Exception {
        String json1 = "{}";
        String json2 = "{}";
        String expected = "{\n}";

        Path file1 = Files.createTempFile("test1", ".json");
        Path file2 = Files.createTempFile("test2", ".json");

        Files.writeString(file1, json1);
        Files.writeString(file2, json2);

        String actual = Differ.generate(file1.toString(), file2.toString()).trim();
        assertEquals(expected, actual);
    }

    @Test
    void testPositiveGenerateDiff() throws Exception {
        String json1 = "{}";
        String json2 = "{\"key\" : \"value\"}";
        String expected = "{\n  + key: value\n}";

        Path file1 = Files.createTempFile("test1", ".json");
        Path file2 = Files.createTempFile("test2", ".json");

        Files.writeString(file1, json1);
        Files.writeString(file2, json2);

        String actual = Differ.generate(file1.toString(), file2.toString()).trim();
        assertEquals(expected.trim().replaceAll("\\R", "\n"), actual.trim().replaceAll("\\R", "\n"));
    }

    @Test
    void testNegativeGenerateDiff() throws Exception {
        String json1 = "{\"key\" : \"value\"}";
        String json2 = "{}";
        String expected = "{\n  - key: value\n}";

        Path file1 = Files.createTempFile("test1", ".json");
        Path file2 = Files.createTempFile("test2", ".json");

        Files.writeString(file1, json1);
        Files.writeString(file2, json2);

        String actual = Differ.generate(file1.toString(), file2.toString()).trim();
        assertEquals(expected.trim().replaceAll("\\R", "\n"), actual.trim().replaceAll("\\R", "\n"));
    }

    @Test
    void testNullKeyGenerateDiff() throws Exception {
        String json1 = "{\"key\" : null}";
        String json2 = "{}";
        String expected = "{\n  - key: null\n}";

        Path file1 = Files.createTempFile("test1", ".json");
        Path file2 = Files.createTempFile("test2", ".json");

        Files.writeString(file1, json1);
        Files.writeString(file2, json2);

        String actual = Differ.generate(file1.toString(), file2.toString()).trim();
        assertEquals(expected.trim().replaceAll("\\R", "\n"), actual.trim().replaceAll("\\R", "\n"));
    }

    @Test
    void testGenerateNestedJsonDiff() throws Exception {
        Path file1 = Path.of("src/test/resources/fixtures/nested1.json");
        Path file2 = Path.of("src/test/resources/fixtures/nested2.json");
        Path expectedFile = Path.of("src/test/resources/fixtures/expectedNestedStylish.txt");

        String expected = Files.readString(expectedFile).trim();
        String actual = Differ.generate(file1.toString(), file2.toString(), "stylish").trim(); // Явно указываем формат

        assertEquals(expected.trim().replaceAll("\\R", "\n"),
                actual.trim().replaceAll("\\R", "\n"));
    }
}
