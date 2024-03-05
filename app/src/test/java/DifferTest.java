import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class DifferTest {
    private static String resultPlain;
    private static String resultStylish;
    private static String resultJson;

    @BeforeAll
    public static void beforeAll() throws IOException {
        String path1 = "src/test/resources/resultJson.txt";
        Path path1Normalize = Paths.get(path1).normalize();
        resultJson = Files.readString(path1Normalize);

        String path2 = "src/test/resources/resultPlain.txt";
        Path path2Normalize = Paths.get(path2).normalize();
        resultPlain = Files.readString(path2Normalize);

        String path3 = "src/test/resources/resultStylish.txt";
        Path path3Normalize = Paths.get(path3).normalize();
        resultStylish = Files.readString(path3Normalize);
    }

    @Test
    public void testStylishFormat() throws Exception {
        var coll = Differ.generate("src/test/resources/file3.json",
                "src/test/resources/file4.json", "stylish");
        assertEquals(resultStylish, coll);
    }

    @Test
    public void testDefaultFormat() throws Exception {
        var coll = Differ.generate("src/test/resources/file3.json",
                "src/test/resources/file4.json");
        assertEquals(resultStylish, coll);
    }

    @Test
    public void testPlainFormat() throws Exception {
        var coll = Differ.generate("src/test/resources/file3.json",
                "src/test/resources/file4.json", "plain");
        assertEquals(resultPlain, coll);
    }

    @Test
    public void testJsonFormat() throws Exception {
        var coll = Differ.generate("src/test/resources/file3.json",
                "src/test/resources/file4.json", "json");
        assertEquals(resultJson, coll);
    }

    @Test
    public void testYmlJsonFormat() throws Exception {
        var coll = Differ.generate("src/test/resources/file1.yml",
                "src/test/resources/file2.yml", "json");
        assertEquals(resultJson, coll);
    }

    @Test
    public void testYmlPlainFormat() throws Exception {
        var coll = Differ.generate("src/test/resources/file1.yml",
                "src/test/resources/file2.yml", "plain");
        assertEquals(resultPlain, coll);
    }

    @Test
    public void testYmlStylishFormat() throws Exception {
        var coll = Differ.generate("src/test/resources/file1.yml",
                "src/test/resources/file2.yml", "stylish");
        assertEquals(resultStylish, coll);
    }

    @Test
    public void testYmlDefaultFormat() throws Exception {
        var coll = Differ.generate("src/test/resources/file1.yml",
                "src/test/resources/file2.yml");
        assertEquals(resultStylish, coll);
    }
}
