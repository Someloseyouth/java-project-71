package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatter(String format, List<Map<String, Object>> diffTemp) throws Exception {
        switch (format) {
            case "plain":
                return Plain.plain(diffTemp);
            case "stylish":
                return Stylish.stylish(diffTemp);
            case "json":
                return Json.json(diffTemp);
            default:
                return null;
        }
    }
}
