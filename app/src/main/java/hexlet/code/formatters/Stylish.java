package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylish(List<Map<String, Object>> formationDiff) {
        StringBuilder diff = new StringBuilder();
        diff.append("{\n");
        for (var element : formationDiff) {
            switch ((String) element.get("status")) {
                case "added":
                    diff.append("  ");
                    diff.append("+ " + element.get("key") + ": ");
                    diff.append(element.get("value") + "\n");
                    break;
                case "deleted":
                    diff.append("  ");
                    diff.append("- ");
                    diff.append(element.get("key") + ": ");
                    diff.append(element.get("value") + "\n");
                    break;
                case "unchanged":
                    diff.append("  ");
                    diff.append("  " + element.get("key") + ": ");
                    diff.append(element.get("newValue") + "\n");
                    break;
                case "changed":
                    diff.append("  ");
                    diff.append("- ");
                    diff.append(element.get("key") + ": ");
                    diff.append(element.get("oldValue") + "\n");
                    diff.append("  ");
                    diff.append("+ ");
                    diff.append(element.get("key") + ": ");
                    diff.append(element.get("newValue") + "\n");
                    break;
                default:
                    return null;
            }
        }
        diff.append("}");
        return diff.toString();
    }
}
