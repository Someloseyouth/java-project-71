package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filepath) throws IOException {
        String content = Files.readString(Path.of(filepath));
        String fileExtension = getFileExtension(filepath);

        ObjectMapper objectMapper = switch (fileExtension) {
            case "json" -> new ObjectMapper();
            case "yml", "yaml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IllegalArgumentException("Unsupported file format: " + fileExtension);
        };

        if (content.isBlank()) {
            return new HashMap<>();
        } else {
            return objectMapper.readValue(content, Map.class);
        }
    }

    private static String getFileExtension(String filepath) {
        int lastDotIndex = filepath.lastIndexOf('.');
        if (lastDotIndex == -1) {
            throw new IllegalArgumentException("File does not have an extension: " + filepath);
        }
        return filepath.substring(lastDotIndex + 1).toLowerCase();
    }

}
