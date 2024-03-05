package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> selectParser(String content, String filePath) throws Exception {
        int index = filePath.indexOf(".");
        String format = filePath.substring(index + 1);
        switch (format) {
            case "json":
                return parserJson(content);
            case "yml":
                return parserYml(content);
            default:
                throw new Exception("Invalid file format");
        }
    }

    public static Map<String, Object> parserJson(String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(content, Map.class);
        return data;
    }

    public static Map<String, Object> parserYml(String content) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        Map<String, Object> data = mapper.readValue(content, Map.class);
        return data;
    }
}
