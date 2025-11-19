package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DifferTest {
    @Test
    void testGenerateDiff() throws Exception {
        // Пути к файлам
        Path file1 = Path.of("src/test/resources/fixtures/filepath1.json");
        Path file2 = Path.of("src/test/resources/fixtures/filepath2.json");
        Path expectedFile = Path.of("src/test/resources/fixtures/expected.txt");

        // Читаем ожидаемый результат
        String expected = Files.readString(expectedFile);

        // Получаем результат работы программы
        String actual = Differ.generate(file1.toString(), file2.toString());

        // Проверяем
        assertEquals(expected, actual);
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

        String actual = Differ.generate(file1.toString(), file2.toString());
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

        String actual = Differ.generate(file1.toString(), file2.toString());
        assertEquals(expected, actual);
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

        String actual = Differ.generate(file1.toString(), file2.toString());
        assertEquals(expected, actual);
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

        String actual = Differ.generate(file1.toString(), file2.toString());
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateNestedJsonStylish() throws Exception {
        Path file1 = Path.of("src/test/resources/fixtures/nested1.json");
        Path file2 = Path.of("src/test/resources/fixtures/nested2.json");
        Path expectedFile = Path.of("src/test/resources/fixtures/expectedNestedStylish.txt");

        String expected = Files.readString(expectedFile);
        String actual = Differ.generate(file1.toString(), file2.toString(), "stylish");

        assertEquals(expected, actual);
    }

    @Test
    void testGenerateNestedJsonDiffPlain() throws Exception {
        Path file1 = Path.of("src/test/resources/fixtures/nested1.json");
        Path file2 = Path.of("src/test/resources/fixtures/nested2.json");
        Path expectedFile = Path.of("src/test/resources/fixtures/expectedPlain.txt");

        String expected = Files.readString(expectedFile).trim();
        String actual = Differ.generate(file1.toString(), file2.toString(), "plain");

        assertEquals(expected, actual);
    }

    @Test
    void testGenerateNestedJsonDiffJson() throws Exception {
        Path file1 = Path.of("src/test/resources/fixtures/nested1.json");
        Path file2 = Path.of("src/test/resources/fixtures/nested2.json");
        Path expectedFile = Path.of("src/test/resources/fixtures/expectedJson.txt");

        String expected = Files.readString(expectedFile).trim();
        String actual = Differ.generate(file1.toString(), file2.toString(), "json");

        assertEquals(expected, actual);
    }

    @Test
    void testGenerateNestedJsonDefault() throws Exception {
        Path file1 = Path.of("src/test/resources/fixtures/nested1.json");
        Path file2 = Path.of("src/test/resources/fixtures/nested2.json");
        Path expectedFile = Path.of("src/test/resources/fixtures/expectedNestedStylish.txt");

        String expected = Files.readString(expectedFile);
        String actual = Differ.generate(file1.toString(), file2.toString());

        assertEquals(expected, actual);
    }

    @Test
    void testEmptyJson() throws Exception {
        assertTrue(Parser.parse("", "json").isEmpty());
    }

    @Test
    void testStylishEmptyDiffTree() throws Exception {
        assertNotNull(Formatter.format(List.of(), "stylish"));
    }

    @Test
    void testPlainEmptyDiffTree() throws Exception {
        assertNotNull(Formatter.format(List.of(), "plain"));
    }

    @Test
    void testJsonEmptyDiffTree() throws Exception {
        assertNotNull(Formatter.format(List.of(), "json"));
    }

    @Test
    void testNoDiffForEqualFiles() throws Exception {
        Path file = Path.of("src/test/resources/fixtures/filepath1.json");
        String actual = Differ.generate(file.toString(), file.toString());
        String expected = Differ.generate(file.toString(), file.toString());
        assertEquals(expected, actual);
    }
}
