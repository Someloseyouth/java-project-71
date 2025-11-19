package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String inputText, String format) throws IOException {
        ObjectMapper objectMapper = switch (format.toLowerCase()) {
            case "json" -> new ObjectMapper();
            case "yml", "yaml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };

        if (inputText.isBlank()) {
            return new HashMap<>();
        } else {
            return objectMapper.readValue(inputText, Map.class);
        }
    }
}
