package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class FormationDiff {
    public static List<Map<String, Object>> formationDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        List<Map<String, Object>> diffTemp = new ArrayList<>();
        for (var key : keys) {
            Map<String, Object> temp = new HashMap<>();
            if (!data1.containsKey(key) && data2.containsKey(key)) {
                temp.put("key", key);
                temp.put("status", "added");
                temp.put("value", data2.get(key));
            } else if (data1.containsKey(key) && !data2.containsKey(key)) {
                temp.put("key", key);
                temp.put("status", "deleted");
                temp.put("value", data1.get(key));
            } else if ((data1.containsKey(key) && data2.containsKey(key))
                    && (!Objects.equals(data1.get(key), data2.get(key)))) {
                temp.put("key", key);
                temp.put("status", "changed");
                temp.put("newValue", data2.get(key));
                temp.put("oldValue", data1.get(key));
            } else if (data1.containsKey(key) && data2.containsKey(key)) {
                temp.put("key", key);
                temp.put("status", "unchanged");
                temp.put("newValue", data2.get(key));
                temp.put("oldValue", data1.get(key));
            }
            diffTemp.add(temp);
        }
        return diffTemp;
    }
}
